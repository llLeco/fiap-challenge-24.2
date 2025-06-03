import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';
import Eventos from './pages/Eventos';
import Regioes from './pages/Regioes';
import Historico from './pages/Historico';

const App = () => {
  return (
    <>
      <Navbar />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/login" element={<Login />} />
          <Route path="/eventos" element={<Eventos />} />
          <Route path="/regioes" element={<Regioes />} />
          <Route path="/historico" element={<Historico />} />
        </Routes>
      </div>
    </>
  );
};

export default App;
