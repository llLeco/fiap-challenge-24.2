import React, { useEffect, useState } from 'react';
import { getEventos } from '../services/api';

const Historico = () => {
  const [eventos, setEventos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [filtro, setFiltro] = useState({ tipo: '', data: '' });

  useEffect(() => {
    getEventos()
      .then((res) => {
        setEventos(res.data);
        setLoading(false);
      })
      .catch(() => {
        setEventos([]);
        setLoading(false);
      });
  }, []);

  const filtrados = eventos.filter((e) => {
    return (
      (!filtro.tipo || e.tipo === filtro.tipo) &&
      (!filtro.data || e.data.startsWith(filtro.data))
    );
  });

  if (loading) {
    return (
      <div className="text-center">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Carregando...</span>
        </div>
      </div>
    );
  }

  return (
    <div>
      <h2>Histórico de Eventos</h2>
      
      <div className="card mb-4">
        <div className="card-body">
          <h5 className="card-title">Filtros</h5>
          <div className="row g-3">
            <div className="col-md-6">
              <label className="form-label">Tipo de Evento</label>
              <select
                className="form-select"
                value={filtro.tipo}
                onChange={(e) => setFiltro({ ...filtro, tipo: e.target.value })}
              >
                <option value="">Todos os tipos</option>
                <option value="enchente">Enchente</option>
                <option value="calor extremo">Calor Extremo</option>
                <option value="terremoto">Terremoto</option>
                <option value="vendaval">Vendaval</option>
                <option value="seca">Seca</option>
              </select>
            </div>
            <div className="col-md-6">
              <label className="form-label">Data</label>
              <input
                type="date"
                className="form-control"
                value={filtro.data}
                onChange={(e) => setFiltro({ ...filtro, data: e.target.value })}
              />
            </div>
          </div>
          <div className="mt-3">
            <button 
              className="btn btn-outline-secondary" 
              onClick={() => setFiltro({ tipo: '', data: '' })}
            >
              Limpar Filtros
            </button>
          </div>
        </div>
      </div>

      <div className="d-flex justify-content-between align-items-center mb-3">
        <h3>Resultados ({filtrados.length} de {eventos.length})</h3>
      </div>
      
      {filtrados.length === 0 ? (
        <div className="alert alert-info">
          {eventos.length === 0 
            ? 'Nenhum evento registrado ainda.' 
            : 'Nenhum evento encontrado com os filtros aplicados.'
          }
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead className="table-dark">
              <tr>
                <th>Tipo</th>
                <th>Intensidade</th>
                <th>Data/Hora</th>
                <th>Região</th>
              </tr>
            </thead>
            <tbody>
              {filtrados.map((e) => (
                <tr key={e.id}>
                  <td>
                    <span className="badge bg-primary">{e.tipo}</span>
                  </td>
                  <td>
                    <span className={`badge ${
                      e.intensidade === 'Baixa' ? 'bg-success' :
                      e.intensidade === 'Moderada' ? 'bg-warning' :
                      e.intensidade === 'Alta' ? 'bg-danger' : 'bg-dark'
                    }`}>
                      {e.intensidade}
                    </span>
                  </td>
                  <td>{new Date(e.data).toLocaleString('pt-BR')}</td>
                  <td>
                    <i className="bi bi-geo-alt"></i> {e.regiao}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default Historico;
