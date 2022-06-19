package br.edu.ifms.cadastroanimais.mediator;

import javax.swing.JButton;

/**
 *
 * @author gustavo
 */
public class ButtonAnimalMediator {

    private JButton btAlterar;
    private JButton btExcluir;

    public void registerAlterar(JButton btAlterar) {
        this.btAlterar = btAlterar;
    }

    public void registerExcluir(JButton btExcluir) {
        this.btExcluir = btExcluir;
    }

    public void ativarBotoesConsulta() {
        this.btAlterar.setVisible(true);
        this.btExcluir.setVisible(true);
    }

    public void desativarBotoes() {
        this.btAlterar.setVisible(false);
        this.btExcluir.setVisible(false);
    }
}
