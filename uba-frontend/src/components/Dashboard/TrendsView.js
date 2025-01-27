import React, { useEffect, useState } from 'react';
import { Line } from 'react-chartjs-2';
import { getTrends } from '../../services/api';
import './TrendsView.css';

const TrendsView = () => {
    const [trendData, setTrendData] = useState({
        labels: [],
        datasets: [{
            label: 'Anomalies',
            data: [],
            borderColor: 'rgba(255, 99, 132, 1)',
            fill: false
        }]
    });

    useEffect(() => {
        const fetchTrends = async () => {
            try {
                const data = await getTrends();
                setTrendData({
                    labels: data.labels,
                    datasets: [{
                        ...trendData.datasets[0],
                        data: data.values
                    }]
                });
            } catch (error) {
                console.error('Error fetching trends:', error);
            }
        };
        fetchTrends();
    }, []);

    return (
        <div className="trends-container">
            <h2>Anomaly Trends Over Time</h2>
            <div className="chart-container">
                <Line data={trendData} options={{
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }} />
            </div>
        </div>
    );
};

export default TrendsView; 