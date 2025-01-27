import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard/Dashboard';
import RuleList from './components/Rules/RuleList';
import RuleCreation from './components/Rules/RuleCreation';
import Navbar from './components/Navbar/Navbar';

function App() {
    return (
        <Router>
            <div className="app-container">
                <Navbar />
                <div className="main-content">
                    <Routes>
                        <Route path="/" element={<Dashboard />} />
                        <Route path="/rules" element={<RuleList />} />
                        <Route path="/rules/new" element={<RuleCreation />} />
                        <Route path="/rules/edit/:id" element={<RuleCreation />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App; 