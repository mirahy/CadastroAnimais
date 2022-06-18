/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.cadastroanimais.model;

import br.edu.ifms.cadastroanimais.dao.AnimalDao;
import br.edu.ifms.cadastroanimais.dao.IAnimalDao;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gustavo
 */
public class AnimalHibernateTableModel extends AbstractTableModel {

    private IAnimalDao dao;
    private List<Animal> lista;
    private String[] colunas = {"Id", "nome", "raca", "sexo", "tamanho"};

    public AnimalHibernateTableModel() {
        dao = new AnimalDao();
        lista = dao.listar();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Animal obj = lista.get(row);
        switch (col) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getNome();
            case 2:
                return obj.getRaca();
            case 3:
                return obj.getSexo();
            case 4:
                return obj.getTamanho();
            default:
                return "";
        }
    }

    public void refresh(String nome) {
        lista.clear();
        lista.addAll(dao.buscarPorNome(nome));
        fireTableStructureChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 0) {
            return Long.class;
        }
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
