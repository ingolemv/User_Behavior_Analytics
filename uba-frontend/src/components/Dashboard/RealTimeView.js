import React, { useEffect, useState } from 'react';
import { getActivities } from '../../services/api';
import './RealTimeView.css';

const RealTimeView = () => {
    const [activities, setActivities] = useState([]);

    useEffect(() => {
        const fetchActivities = async () => {
            try {
                const data = await getActivities();
                setActivities(data);
            } catch (error) {
                console.error('Error fetching activities:', error);
            }
        };

        fetchActivities();
        const interval = setInterval(fetchActivities, 5000);
        return () => clearInterval(interval);
    }, []);

    return (
        <div className="real-time-container">
            <h2>Real-Time User Activities</h2>
            <div className="activities-list">
                {activities.map(activity => (
                    <div key={activity.id} className={`activity-item ${activity.isAnomaly ? 'anomaly' : ''}`}>
                        <div className="activity-header">
                            <span className="user">{activity.userId}</span>
                            <span className="type">{activity.activityType}</span>
                            <span className="time">{new Date(activity.timestamp).toLocaleTimeString()}</span>
                        </div>
                        <div className="activity-details">
                            <span>IP: {activity.ipAddress}</span>
                            <span>Location: {activity.location}</span>
                            <span>Risk: {activity.riskScore.toFixed(2)}</span>
                        </div>
                        {activity.isAnomaly && (
                            <div className="anomaly-alert">
                                ⚠️ Anomaly Detected: {activity.anomalyReason}
                            </div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default RealTimeView; 