import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const ReportCreation = () => {
    const [report, setReport] = useState({
        name: '',
        description: '',
        reportType: 'Daily',
        startDate: '',
        endDate: ''
    });
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('/api/reports', report);
            navigate('/reports');
        } catch (error) {
            console.error('Error creating report:', error);
        }
    };

    return (
        <div className="report-creation">
            <h2>Create New Report</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name:</label>
                    <input
                        type="text"
                        value={report.name}
                        onChange={(e) => setReport({...report, name: e.target.value})}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Description:</label>
                    <textarea
                        value={report.description}
                        onChange={(e) => setReport({...report, description: e.target.value})}
                    />
                </div>
                <div className="form-group">
                    <label>Report Type:</label>
                    <select
                        value={report.reportType}
                        onChange={(e) => setReport({...report, reportType: e.target.value})}
                    >
                        <option value="Daily">Daily</option>
                        <option value="Weekly">Weekly</option>
                        <option value="Monthly">Monthly</option>
                        <option value="Custom">Custom</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Start Date:</label>
                    <input
                        type="date"
                        value={report.startDate}
                        onChange={(e) => setReport({...report, startDate: e.target.value})}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>End Date:</label>
                    <input
                        type="date"
                        value={report.endDate}
                        onChange={(e) => setReport({...report, endDate: e.target.value})}
                        required
                    />
                </div>
                <button type="submit">Create Report</button>
            </form>
        </div>
    );
};

export default ReportCreation; 