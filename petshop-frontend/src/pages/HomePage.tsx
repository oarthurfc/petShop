import React, { useState } from 'react';
import axios from 'axios';
import '../App.css';
import '../services/api'
import { Calendar, PawPrint, Search } from 'lucide-react';

const HomePage: React.FC = () => {
  const [data, setData] = useState('');
  const [qtdPequenos, setQtdPequenos] = useState(0);
  const [qtdGrandes, setQtdGrandes] = useState(0);
  const [result, setResult] = useState<{ nome: string; precoTotal: number } | null>(null);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.get('http://127.0.0.1:8080/api/petshops/melhor-petshop', {
        params: { data, qtdPequenos, qtdGrandes }
      });
      setResult(response.data);
    } catch (error) {
      console.error('Erro ao encontrar PetShop!', error);
    }
  };

  return (
    <div className="home-page">
      <h1>Encontre o <br/>Melhor PetShop</h1>
      <form className="form-container" onSubmit={handleSubmit}>
        <div className="box-input">
          <p>Data</p>
          <div className="input-content">
            <Calendar className="input-icon"/>
            <input
              type="text"
              placeholder="Data (dd/mm/yyyy)"
              value={data}
              onChange={(e) => setData(e.target.value)}
            />
          </div>
        </div>
        <div className="box-num-caes">
          <div className="box-input">
            <p>Cães pequenos</p>
            <div className="input-content">
              <PawPrint className="input-icon"/>
              <input
                type="number"
                placeholder="Quantidade de cães pequenos"
                value={qtdPequenos}
                onChange={(e) => setQtdPequenos(Number(e.target.value))}
              />
            </div>
          </div>
          <div className="box-iput">
            <p>Cães grandes</p>
            <div className="input-content">
              <PawPrint className="input-icon"/>
              <input
                type="number"
                placeholder="Quantidade de cães grandes"
                value={qtdGrandes}
                onChange={(e) => setQtdGrandes(Number(e.target.value))}
              />
            </div>
          </div>
        </div>
        <button type="submit">
            <Search className="btn-icon"/>
            Buscar
        </button>
      </form>
      {result && (
        <div className="result-container">
          <h2>Melhor PetShop:<br/> {result.nome}</h2>
          <p><b>Preço Total: R${result.precoTotal}</b></p>
        </div>
      )}
    </div>
  );
};

export default HomePage;
