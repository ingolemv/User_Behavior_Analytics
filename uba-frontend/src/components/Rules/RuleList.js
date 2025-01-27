import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getRules, deleteRule } from '../../services/api';

const RuleList = () => {
    const [rules, setRules] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchRules = async () => {
            try {
                const data = await getRules();
                setRules(data);
            } catch (error) {
                console.error('Error fetching rules:', error);
            }
        };
        fetchRules();
    }, []);

    const handleDelete = async (id) => {
        try {
            await deleteRule(id);
            setRules(rules.filter(rule => rule.id !== id));
        } catch (error) {
            console.error('Error deleting rule:', error);
        }
    };

    return (
        <div className="rule-list-container">
            <h2>Rules Management</h2>
            <button onClick={() => navigate('/rules/new')} className="create-btn">
                Create New Rule
            </button>
            <table className="rules-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Condition</th>
                        <th>Action</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {rules.map(rule => (
                        <tr key={rule.id}>
                            <td>{rule.name}</td>
                            <td>{rule.description}</td>
                            <td>{rule.condition}</td>
                            <td>{rule.action}</td>
                            <td>{rule.active ? 'Active' : 'Inactive'}</td>
                            <td>
                                <button 
                                    onClick={() => navigate(`/rules/edit/${rule.id}`)}
                                    className="edit-btn"
                                >
                                    Edit
                                </button>
                                <button 
                                    onClick={() => handleDelete(rule.id)}
                                    className="delete-btn"
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default RuleList; 