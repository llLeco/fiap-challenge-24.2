import React, { useEffect, useState } from 'react';
import { getEventos } from '../services/api';

const Historico = () => {
  const [eventos, setEventos] = useState([]);
  const [filtro, setFiltro] = useState({ tipo: '', data: '' });

  useEffect(() => {
    getEventos()
      .then((res) => setEventos(res.data))
      .catch(() => setEventos([]));
  }, []);

  const filtrados = eventos.filter((e) => {
    return (
      (!filtro.tipo || e.tipo === filtro.tipo) &&
      (!filtro.data || e.data.startsWith(filtro.data))
    );
  });

  return (
    <div>
      <h2>Histórico de Eventos</h2>
      <div className="row g-3 mb-3">
        <div className="col-md-6">
          <label className="form-label">Tipo</label>
          <select
            className="form-select"
            value={filtro.tipo}
            onChange={(e) => setFiltro({ ...filtro, tipo: e.target.value })}
          >
            <option value="">Todos</option>
            <option value="enchente">Enchente</option>
            <option value="calor extremo">Calor Extremo</option>
            <option value="terremoto">Terremoto</option>
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
      <ul className="list-group">
        {filtrados.map((e) => (
          <li key={e.id} className="list-group-item">
            {e.tipo} - {e.data} ({e.regiao})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Historico;
