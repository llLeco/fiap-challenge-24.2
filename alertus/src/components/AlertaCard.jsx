import React from 'react';

const levelClass = {
  Baixo: 'bg-success text-white',
  Medio: 'bg-warning text-dark',
  Alto: 'bg-danger text-white',
};

const AlertaCard = ({ alerta }) => (
  <div className={`card mb-3 ${levelClass[alerta.nivel] || ''}`}>
    <div className="card-body">
      <h5 className="card-title">{alerta.mensagem}</h5>
      <p className="card-text">
        <strong>Nível:</strong> {alerta.nivel} | <strong>Região:</strong> {alerta.regiao}
      </p>
      <p className="card-text">
        <small className="text-muted">{alerta.data}</small>
      </p>
    </div>
  </div>
);

export default AlertaCard;
