import React, { useEffect, useState } from 'react';
import { getEventos, postEvento } from '../services/api';

const Eventos = () => {
  const [eventos, setEventos] = useState([]);
  const [form, setForm] = useState({
    tipo: '',
    intensidade: '',
    data: '',
    regiao: '',
  });

  useEffect(() => {
    getEventos()
      .then((res) => setEventos(res.data))
      .catch(() => setEventos([]));
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    postEvento(form)
      .then(() => alert('Evento cadastrado!'))
      .catch(() => alert('Falha ao cadastrar.')); // Fake result
  };

  return (
    <div>
      <h2>Cadastro de Evento</h2>
      <form onSubmit={handleSubmit} className="mb-4">
        <div className="row g-3">
          <div className="col-md-3">
            <label className="form-label">Tipo</label>
            <select className="form-select" name="tipo" value={form.tipo} onChange={handleChange}>
              <option value="">Selecione</option>
              <option value="enchente">Enchente</option>
              <option value="calor extremo">Calor Extremo</option>
              <option value="terremoto">Terremoto</option>
            </select>
          </div>
          <div className="col-md-3">
            <label className="form-label">Intensidade</label>
            <input
              type="text"
              className="form-control"
              name="intensidade"
              value={form.intensidade}
              onChange={handleChange}
            />
          </div>
          <div className="col-md-3">
            <label className="form-label">Data/Hora</label>
            <input
              type="datetime-local"
              className="form-control"
              name="data"
              value={form.data}
              onChange={handleChange}
            />
          </div>
          <div className="col-md-3">
            <label className="form-label">Região</label>
            <input
              type="text"
              className="form-control"
              name="regiao"
              value={form.regiao}
              onChange={handleChange}
            />
          </div>
        </div>
        <button className="btn btn-success mt-3" type="submit">
          Salvar
        </button>
      </form>
      <h3>Eventos Registrados</h3>
      <ul className="list-group">
        {eventos.map((e) => (
          <li key={e.id} className="list-group-item">
            {e.tipo} - {e.data} ({e.regiao})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Eventos;
