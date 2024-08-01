import React, { useState } from 'react';
import axios from 'axios';

const HomePage: React.FC = () => {
    const [nome, setNome] = useState('');
    const [precoTotal, setPrecoTotal] = useState<number | null>(null);
    const [data, setData] = useState('');
    const [qtdPequenos, setQtdPequenos] = useState(0);
    const [qtdGrandes, setQtdGrandes] = useState(0);

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            const response = await axios.get('http://127.0.0.1:8080/api/petshops/melhor-petshop', {
                params: {
                    data,
                    qtdPequenos,
                    qtdGrandes
                }
            });
            console.log(response)
            setNome(response.data.nome);
            setPrecoTotal(response.data.precoTotal);
        } catch (error) {
            console.error("There was an error fetching the pet shop!", error);
        }
    }

    return (
        <div>
            <h1>Melhor Pet Shop</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Data:</label>
                    <input type="text" value={data} onChange={(e) => setData(e.target.value)} placeholder="dd/MM/yyyy" required />
                </div>
                <div>
                    <label>Quantidade de Pequenos:</label>
                    <input type="number" value={qtdPequenos} onChange={(e) => setQtdPequenos(Number(e.target.value))} required />
                </div>
                <div>
                    <label>Quantidade de Grandes:</label>
                    <input type="number" value={qtdGrandes} onChange={(e) => setQtdGrandes(Number(e.target.value))} required />
                </div>
                <button type="submit">Calcular</button>
            </form>
            {nome && precoTotal !== null && (
                <div>
                    <h2>Melhor Pet Shop: {nome}</h2>
                    <p>Preço Total: R${precoTotal}</p>
                </div>
            )}
        </div>
    );
}

export default HomePage;
