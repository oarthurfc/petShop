import React, { useState } from 'react';
import axios from 'axios';
import { DayPicker } from 'react-day-picker';
import 'react-day-picker/dist/style.css';
import '../App.css';
import '../services/api'
import { Calendar, PawPrint, Search, X } from 'lucide-react';

const HomePage: React.FC = () => {
  const [selectedDate, setSelectedDate] = useState<Date | undefined>();
  const [isModalDataOpen, setIsModalData] = useState(false);
  const [qtdPequenos, setQtdPequenos] = useState(0);
  const [qtdGrandes, setQtdGrandes] = useState(0);
  const [result, setResult] = useState<{ nome: string; precoTotal: number } | null>(null);

  function openModalData(){
    setIsModalData(true)
  }

  function closeModalData(){
    setIsModalData(false)
  }

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    if (!selectedDate) {
      console.error('Por favor, selecione uma data.');
      return;
    }

    const formattedDate = selectedDate.toLocaleDateString('pt-BR');

    try {
      const response = await axios.get('http://127.0.0.1:8080/api/petshops/melhor-petshop', {
        params: { data: formattedDate, qtdPequenos, qtdGrandes }
      });
      setResult(response.data);
    } catch (error) {
      console.error('There was an error fetching the pet shop!', error);
    }
  };

  return (
    <div className="home-page">
      <h1>Encontre o <br/>Melhor PetShop</h1>
      <form className="form-container" onSubmit={handleSubmit}>
        <div className="box-input" id="input-data">
          <p>Data</p>
          <div onClick={openModalData} className="input-content" id="abrir-modal">
            <Calendar className="input-icon"/>
            {selectedDate ?(
              <p>{selectedDate.toLocaleDateString('pt-BR')}</p>
            ) : 
              <p>Selecione uma data</p>
            }
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


      {isModalDataOpen && (
        <div className="modal-backdrop">
          <div className="modal-data">
            <X onClick={closeModalData} className="modal-close"/>
            <DayPicker
                mode="single"
                selected={selectedDate}
                onSelect={setSelectedDate}
                footer={selectedDate ? `Data selecionada: ${selectedDate.toLocaleDateString('pt-BR')}` : 'Please pick a date.'}
            />
          </div>
        </div>
      )}


    </div>
  );
};

export default HomePage;
