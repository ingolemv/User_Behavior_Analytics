import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createRule } from '../../services/api';

const RuleCreation = () => {
    const [rule, setRule] = useState({
        name: '',
        description: '',
        condition: '',
        action: 'ALERT',
        active: true
    });
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createRule(rule);
            navigate('/rules');
        } catch (error) {
            console.error('Error creating rule:', error);
        }
    };

    return (
        <div className="rule-creation-container">
            <h2>Create New Rule</h2>
            <form onSubmit={handleSubmit} className="rule-form">
                <div className="form-group">
                    <label>Name:</label>
                    <input
                        type="text"
                        value={rule.name}
                        onChange={(e) => setRule({...rule, name: e.target.value})}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Description:</label>
                    <textarea
                        value={rule.description}
                        onChange={(e) => setRule({...rule, description: e.target.value})}
                    />
                </div>
                <div className="form-group">
                    <label>Condition:</label>
                    <textarea
                        value={rule.condition}
                        onChange={(e) => setRule({...rule, condition: e.target.value})}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Action:</label>
                    <select
                        value={rule.action}
                        onChange={(e) => setRule({...rule, action: e.target.value})}
                    >
                        <option value="ALERT">Alert</option>
                        <option value="BLOCK">Block</option>
                        <option value="LOG">Log</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Active:</label>
                    <input
                        type="checkbox"
                        checked={rule.active}
                        onChange={(e) => setRule({...rule, active: e.target.checked})}
                    />
                </div>
                <button type="submit" className="submit-btn">Create Rule</button>
            </form>
        </div>
    );
};

export default RuleCreation; 