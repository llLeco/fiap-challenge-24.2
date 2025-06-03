import React, { useState } from 'react';
import { postRegiao } from '../services/api';

const Regioes = () => {
  const [form, setForm] = useState({ nome: '', latitude: '', longitude: '' });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    postRegiao(form)
      .then(() => alert('Região cadastrada!'))
      .catch(() => alert('Falha no cadastro.'));
  };

  return (
    <div>
      <h2>Cadastro de Região</h2>
      <form onSubmit={handleSubmit} className="row g-3">
        <div className="col-md-4">
          <label className="form-label">Nome</label>
          <input
            type="text"
            className="form-control"
            name="nome"
            value={form.nome}
            onChange={handleChange}
          />
        </div>
        <div className="col-md-4">
          <label className="form-label">Latitude</label>
          <input
            type="text"
            className="form-control"
            name="latitude"
            value={form.latitude}
            onChange={handleChange}
          />
        </div>
        <div className="col-md-4">
          <label className="form-label">Longitude</label>
          <input
            type="text"
            className="form-control"
            name="longitude"
            value={form.longitude}
            onChange={handleChange}
          />
        </div>
        <div className="col-12">
          <button type="submit" className="btn btn-success mt-3">
            Salvar
          </button>
        </div>
      </form>
    </div>
  );
};

export default Regioes;
