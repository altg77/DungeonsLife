package DugeonsLife;

import javax.swing.*;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Status extends javax.swing.JFrame {
    private Personagem personagem;
    private DefaultListModel<String> listModel;

    
    public Status(Personagem personagem) {
        this.personagem = personagem;
        listModel = new DefaultListModel<>();
        
        initComponents();
        setLocationRelativeTo(null);
        
        setar();
        setarstatus();
        
    }
    
    private void setar(){    
        Nome.setText("NOME: " + personagem.getnome()); // Atualiza o campo de nome
        Classjob.setText("CLASS| JOB: " + personagem.getClasse());
        Titulo.setText("TÌTULO: " + personagem.getTitulo());
        Nivel.setText("NÍVEL: " + personagem.getlevel());
        Classnivel.setText("CLASS NIVEL: " + personagem.getlevelclasse());
        
        HP.setText("HP: " + personagem.getvidamax());
        MB.setText("HP: " + personagem.getmanamax());
        
        updatebars();
    }
    
    private void updatebars(){
        //Vida
            int vidaAtual = personagem.getvida();
            int vidaMaxima = personagem.getvidamax();
        
        // Calculando porcentagem
        int porcentagem = (int) ((vidaAtual / (double) vidaMaxima) * 100);
        
        // Atualizando a JProgressBar e JLabel
        hpbar.setValue(porcentagem);
        hpbar.setString(vidaAtual + " / " + vidaMaxima + "%"); // Mostra "vidaAtual / vidaMaxima"
        
        //HP
            int manaAtual = personagem.getmana();
            int manaMaxima = personagem.getmanamax();
            
        // Calculando porcentagem
        int porcentagem2 = (int) ((manaAtual / (double) manaMaxima) * 100);
        
        // Atualizando a JProgressBar e JLabel
        mbbar.setValue(porcentagem2);
        mbbar.setString(manaAtual + " / " + manaMaxima + "%"); // Mostra "vidaAtual / vidaMaxima"
    }
    
    private void setarstatus(){
        Pontos.setText("PONTOS SOBRANDO: " + personagem.getPontosHabilidade());
         // Carregar os valores atuais dos atributos do jogador nos spinners
        SpinForça.setValue(personagem.getForca());
        SpinAgi.setValue(personagem.getAgilidade());
        SpinPercep.setValue(personagem.getPercepcao());
        SpinVita.setValue(personagem.getVitalidade());
        SpinInte.setValue(personagem.getInteligencia());
    }
     
private void salvarAtributos() {
    int pontosUsados = calcularPontosUsados(); // Calcular quantos pontos de habilidade foram usados
    
    if (pontosUsados <= personagem.getPontosHabilidade()) {
        // Atualizar os atributos do player, ajustando os valores diretamente
        personagem.aumentarForca((Integer) SpinForça.getValue() - personagem.getForca()); // true se for pelo spin
        personagem.aumentarAgilidade((Integer) SpinAgi.getValue() - personagem.getAgilidade());
        personagem.aumentarPercepcao((Integer) SpinPercep.getValue() - personagem.getPercepcao());
        personagem.aumentarVitalidade((Integer) SpinVita.getValue() - personagem.getVitalidade());
        personagem.aumentarInteligencia((Integer) SpinInte.getValue() - personagem.getInteligencia());
        
        // Atualizar os pontos de habilidade restantes
            personagem.adicionarPontosHabilidade(-pontosUsados); // Deduz os pontos usados
            Pontos.setText("" + personagem.getPontosHabilidade()); // Atualiza a interface com o novo valor
            dispose(); // Fecha a janela após salvar
        
    } else {
        JOptionPane.showMessageDialog(this, "Você não tem pontos de habilidade suficientes.");
    }
}

    
     private int calcularPontosUsados() {
    int pontosUsados = 0;
    pontosUsados += (Integer) SpinForça.getValue() - personagem.getForca();
    pontosUsados += (Integer) SpinAgi.getValue() - personagem.getAgilidade();
    pontosUsados += (Integer) SpinPercep.getValue() - personagem.getPercepcao();
    pontosUsados += (Integer) SpinVita.getValue() - personagem.getVitalidade();
    pontosUsados += (Integer) SpinInte.getValue() - personagem.getInteligencia();
    return pontosUsados; // Total de pontos usados
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel = new javax.swing.JPanel();
        Satus = new javax.swing.JLabel();
        SpinForça = new javax.swing.JSpinner();
        Força = new javax.swing.JLabel();
        SpinAgi = new javax.swing.JSpinner();
        Agilidade = new javax.swing.JLabel();
        SpinPercep = new javax.swing.JSpinner();
        Percepcao = new javax.swing.JLabel();
        Vitalidade = new javax.swing.JLabel();
        SpinVita = new javax.swing.JSpinner();
        SpinInte = new javax.swing.JSpinner();
        Inteligencia = new javax.swing.JLabel();
        Pontos = new javax.swing.JLabel();
        Nome = new javax.swing.JLabel();
        Classjob = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        Nivel = new javax.swing.JLabel();
        Cansaco = new javax.swing.JLabel();
        HP = new javax.swing.JLabel();
        hpbar = new javax.swing.JProgressBar();
        MB = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Classnivel = new javax.swing.JLabel();
        mbbar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Status");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Painel.setBackground(new java.awt.Color(0, 51, 255));

        Satus.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Satus.setForeground(new java.awt.Color(255, 255, 255));
        Satus.setText("STATUS");

        SpinForça.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinForça.setToolTipText("");

        Força.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Força.setForeground(new java.awt.Color(255, 255, 255));
        Força.setText("FORÇA:");

        SpinAgi.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinAgi.setToolTipText("");

        Agilidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Agilidade.setForeground(new java.awt.Color(255, 255, 255));
        Agilidade.setText("AGILIDADE:");

        SpinPercep.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinPercep.setToolTipText("");

        Percepcao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Percepcao.setForeground(new java.awt.Color(255, 255, 255));
        Percepcao.setText("PERCEPÇÃO:");

        Vitalidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Vitalidade.setForeground(new java.awt.Color(255, 255, 255));
        Vitalidade.setText("VITALIDADE:");

        SpinVita.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinVita.setToolTipText("");

        SpinInte.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinInte.setToolTipText("");

        Inteligencia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Inteligencia.setForeground(new java.awt.Color(255, 255, 255));
        Inteligencia.setText("INTELIGENCIA:");

        Pontos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Pontos.setForeground(new java.awt.Color(255, 255, 255));
        Pontos.setText("PONTOS SOBRANDO: 3");

        Nome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Nome.setForeground(new java.awt.Color(255, 255, 255));
        Nome.setText("NOME:  NENHUM");

        Classjob.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Classjob.setForeground(new java.awt.Color(255, 255, 255));
        Classjob.setText("CLASS| JOB: NENHUM");

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("TÍTULO: NENHUM");

        Nivel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Nivel.setForeground(new java.awt.Color(255, 255, 255));
        Nivel.setText("NÍVEL: 0");

        Cansaco.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Cansaco.setForeground(new java.awt.Color(255, 255, 255));
        Cansaco.setText("CANSAÇO: 0");

        HP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        HP.setForeground(new java.awt.Color(255, 255, 255));
        HP.setText("HP: 20");

        MB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MB.setForeground(new java.awt.Color(255, 255, 255));
        MB.setText("MB: 10");

        Classnivel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Classnivel.setForeground(new java.awt.Color(255, 255, 255));
        Classnivel.setText("CLASS NIVEL: 0");

        javax.swing.GroupLayout PainelLayout = new javax.swing.GroupLayout(Painel);
        Painel.setLayout(PainelLayout);
        PainelLayout.setHorizontalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Pontos)
                .addGap(27, 27, 27))
            .addGroup(PainelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelLayout.createSequentialGroup()
                        .addComponent(HP)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mbbar, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hpbar, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MB))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nome)
                            .addComponent(Titulo)
                            .addComponent(Classjob))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nivel)
                            .addComponent(Cansaco)
                            .addComponent(Classnivel))
                        .addGap(69, 69, 69))))
            .addGroup(PainelLayout.createSequentialGroup()
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(Satus))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addComponent(Inteligencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinInte, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addComponent(Força)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SpinForça, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addComponent(Agilidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SpinAgi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(49, 49, 49)
                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addComponent(Percepcao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SpinPercep, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addComponent(Vitalidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SpinVita, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelLayout.setVerticalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Satus)
                .addGap(18, 18, 18)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nome)
                    .addComponent(Nivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Classjob)
                    .addComponent(Classnivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Titulo)
                    .addComponent(Cansaco))
                .addGap(29, 29, 29)
                .addComponent(HP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hpbar, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(MB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mbbar, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Força)
                    .addComponent(SpinForça, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Vitalidade)
                    .addComponent(SpinVita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Agilidade)
                    .addComponent(SpinAgi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Percepcao)
                    .addComponent(SpinPercep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Inteligencia)
                    .addComponent(SpinInte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pontos, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        salvarAtributos();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Agilidade;
    private javax.swing.JLabel Cansaco;
    private javax.swing.JLabel Classjob;
    private javax.swing.JLabel Classnivel;
    private javax.swing.JLabel Força;
    private javax.swing.JLabel HP;
    private javax.swing.JLabel Inteligencia;
    private javax.swing.JLabel MB;
    private javax.swing.JLabel Nivel;
    private javax.swing.JLabel Nome;
    private javax.swing.JPanel Painel;
    private javax.swing.JLabel Percepcao;
    private javax.swing.JLabel Pontos;
    private javax.swing.JLabel Satus;
    private javax.swing.JSpinner SpinAgi;
    private javax.swing.JSpinner SpinForça;
    private javax.swing.JSpinner SpinInte;
    private javax.swing.JSpinner SpinPercep;
    private javax.swing.JSpinner SpinVita;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Vitalidade;
    private javax.swing.JProgressBar hpbar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JProgressBar mbbar;
    // End of variables declaration//GEN-END:variables
}
