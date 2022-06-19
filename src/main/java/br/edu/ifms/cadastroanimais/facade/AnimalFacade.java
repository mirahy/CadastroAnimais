package br.edu.ifms.cadastroanimais.facade;

import br.edu.ifms.cadastroanimais.dao.AnimalDao;
import br.edu.ifms.cadastroanimais.dao.IAnimalDao;
import br.edu.ifms.cadastroanimais.model.Animal;
import br.edu.ifms.cadastroanimais.view.TelaFormAnimal;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author gustavo
 */
public class AnimalFacade {

    private IAnimalDao dao;

    public AnimalFacade(IAnimalDao dao) {
        this.dao = dao;
    }

    public AnimalFacade() {
        this(new AnimalDao());
    }

    public TelaFormAnimal abrirFormulario(JFrame frame, AnimalFacade facade) {
        TelaFormAnimal dialog = new TelaFormAnimal(frame, true, facade);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        return dialog;
    }

    public TelaFormAnimal editarFormulario(
            JFrame frame,
            AnimalFacade facade,
            Long id) {
        TelaFormAnimal dialog = abrirFormulario(frame, facade);
        dialog.setId(id);
        return dialog;
    }

    public void carregarDados(
            Long id,
            JTextField txtId,
            JTextField txtNome,
            JTextField txtRaca,
            JTextField txtSexo,
            JTextField txtTamanho) {
        Animal a = dao.buscarPorId(id);
        txtId.setText(a.getId().toString().toUpperCase());
        txtNome.setText(a.getNome().toUpperCase());
        txtRaca.setText(a.getRaca().toUpperCase());
        txtSexo.setText(a.getSexo().toUpperCase());
        txtTamanho.setText(a.getTamanho().toUpperCase());
    }

    public Boolean salvar(
            JTextField txtId,
            JTextField txtNome,
            JTextField txtRaca,
            JTextField txtSexo,
            JTextField txtTamanho) {
        boolean isId = txtId.getText().matches("\\d+");
        Long id = isId ? Long.parseLong(txtId.getText()) : null;

        Animal animal = new Animal();
        animal.setId(id);
        animal.setNome(txtNome.getText().toUpperCase());
        animal.setRaca(txtRaca.getText().toUpperCase());
        animal.setSexo(txtSexo.getText().toUpperCase());
        animal.setTamanho(txtTamanho.getText().toUpperCase());
        if (!isId) {
            dao.inserir(animal);
        } else {
            dao.alterar(animal);
        }

        return Boolean.TRUE;
    }

    public Boolean excluir(JFrame frame, Long id) {
        if (JOptionPane.showConfirmDialog(frame, "Deseja excluir esse registro?",
                "Excluir", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

            dao.excluir(id);

            JOptionPane.showMessageDialog(frame, "Registro excluído com sucesso!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }

}
