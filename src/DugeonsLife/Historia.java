package DugeonsLife;

import javax.swing.JOptionPane;

public class Historia {
    private Gui gui;
    private Skills skills;  // Referência para a classe Skills
    private Personagem personagem;
    private int progresso;  // Controla o progresso na história

    
    public Historia(Gui gui, Personagem personagem) {
        this.gui = gui;
        this.personagem = personagem;
        this.skills = new Skills(personagem, this);  // Inicializa a classe Skills
        this.progresso = 0;  // Inicia o progresso da história

    }
    
     public void mostrarMensagemSkill() {
        // Exibe a mensagem dependendo do progresso
        String mensagem;
        if (progresso == 0) {
            mensagem = "Você deseja receber essa skill? (Spirit)\n1 - Sim\n2 - Não";
        } else if (progresso == 1) {
            mensagem = "Você encontrou um grimório antigo. Deseja aprender a skill 'Fireball'? \n1 - Sim\n2 - Não";
        } else {
            mensagem = "Não há mais habilidades para aprender no momento.";
        }
        gui.exibirMensagem(mensagem);  // Chama o método da GUI para exibir a mensagem
    }
    
    
     // Processa a escolha do jogador
    public void processarEscolha(int escolha) {
        if (progresso == 0) {
            if (escolha == 1) {
                // O jogador escolheu "Sim" para a skill Spirit
                String nomeSkill = "SPIRIT";
                String descricaoSkill = "Aumenta a regeneração de mana em 20%.";

                // Atualiza a skill em Skills
                skills.atualizarSkill("passiva", nomeSkill, descricaoSkill); // Atualiza a skill e a descrição

                // Exibe a mensagem de confirmação na GUIw
                gui.exibirDialog("Você adquiriu a skill: " + nomeSkill + "\n" + descricaoSkill);
            } 
            progresso++;  // Avança para a próxima etapa
        } else if (progresso == 1) {
            if (escolha == 1) {
                // O jogador escolheu "Sim" para aprender Fireball
                String nomeSkill = "ESPÍRITO INFLEXÍVEL";
                String descricaoSkill = "Quando o HP está abaixo de 30%, todo o dano recebido é reduzido em 50%.";

                // Atualiza a skill em Skills
                skills.atualizarSkill("passiva", nomeSkill, descricaoSkill); // Atualiza a skill e a descrição

                // Exibe a mensagem de confirmação na GUI
                gui.exibirDialog("Você adquiriu a skill: " + nomeSkill + "\n" + descricaoSkill);
            } 
            progresso++;  // Avança para o próximo estágio
        }
        // Você pode adicionar mais progresso ou eventos conforme necessário
        mostrarMensagemSkill();
    }
    
}
