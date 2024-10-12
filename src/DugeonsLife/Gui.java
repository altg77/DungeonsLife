package DugeonsLife;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Gui extends javax.swing.JFrame {
    private Personagem personagem;
    private Historia historia;
    private Skills skillWindow;
    private Inventario InventoryWindow;
    private Equipados EquipWindow;


    public Gui(String nome) {
        initComponents();
        setLocationRelativeTo(null);
        personagem = new Personagem(nome);      
        
        Nomeplayer.setText("" + nome); // Atualiza o campo de nome
        setar();
        
        // Inicializa a classe Historia com referência à GUI
        historia = new Historia(this, personagem); // Passa a instância de personagem
    
        // Mostra a mensagem da skill assim que a interface é iniciada
        historia.mostrarMensagemSkill();
        
        // Adicionando ouvintes de ação aos botões
        um.addActionListener(e -> processarEscolha(1));
        dois.addActionListener(e -> processarEscolha(2));
        tres.addActionListener(e -> processarEscolha(3));
        quatro.addActionListener(e -> processarEscolha(4));
        cinco.addActionListener(e -> processarEscolha(5));
        
        // Definindo o foco para o JPanel
        jTextArea1.requestFocusInWindow();
        jTextArea1.setFocusable(true);
        
        // Adiciona um WindowListener para garantir que o JTextArea sempre receba foco
        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                jTextArea1.requestFocusInWindow();
            }
        });
     }
    
    // Método para processar a escolha do jogador
    private void processarEscolha(int escolha) {
        historia.processarEscolha(escolha); // Chama o método da História com a escolha
    }
    
    public void exibirMensagem(String mensagem) {
        jTextArea1.setText(mensagem);  // Atualiza a área de texto com a mensagem passada
        jTextArea1.requestFocusInWindow(); // Garante que o JTextArea tenha foco após exibir a mensagem

    }
    
    public void exibirDialog(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "ALERT", JOptionPane.INFORMATION_MESSAGE);
        jTextArea1.requestFocusInWindow(); // Garante que o JTextArea tenha foco após exibir a mensagem
    }
    
    private void abrirJanelaLoja() {
         // Verifica se o nível do personagem é 10
    if (personagem.getlevel() >= 10) {
        Loja loja = new Loja(personagem);

        // Adiciona um listener para a janela de status
        loja.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setar(); // Atualiza a GUI
            }
        });
        
        loja.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Isso garante que apenas a janela Loja feche12
        loja.setVisible(true); // Torna a janela da loja visível
    } else {
        // Exibe uma mensagem se o personagem não atingiu o nível necessário
        exibirDialog("Você precisa estar no nível 10 para acessar a loja.");
    }
    }
    
    private void abrirJanelaequip() {
        EquipWindow = new Equipados(personagem);
        
        // Adiciona um listener para a janela de status
        EquipWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setar(); // Atualiza a GUI
            }
        });

        EquipWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Isso garante que apenas a janela Loja feche12
        EquipWindow.setVisible(true);
    }
    
    private void abrirJanelasinve() {
        InventoryWindow = new Inventario(personagem);
        
        // Adiciona um listener para a janela de status
        InventoryWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setar(); // Atualiza a GUI
            }
        });

        InventoryWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Isso garante que apenas a janela Loja feche12
        InventoryWindow.setVisible(true);
    }
    
    private void abrirJanelaskills() {
        skillWindow = new Skills(personagem, historia);
        
        // Adiciona um listener para a janela de status
        skillWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setar(); // Atualiza a GUI
            }
        });

        skillWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Isso garante que apenas a janela Loja feche12
        skillWindow.setVisible(true);
    }
    
    private void abrirJanelaStatus() {
        Status status = new Status(personagem);
          // Adiciona um listener para a janela de status
       // Adiciona um WindowListener para atualizar a GUI quando a janela de status for fechada
    status.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            setar(); // Atualiza a GUI
        }
    });

        status.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Isso garante que apenas a janela Status feche
        status.setVisible(true);
    }
    
    private void setar(){
        Força.setText("STR: " + personagem.getForca());
        Agilidade.setText("AGI: " + personagem.getAgilidade());
        Percepcao.setText("PER: " + personagem.getPercepcao());
        Vitalidade.setText("VIT: " + personagem.getVitalidade());
        Inteligencia.setText("INT: " + personagem.getInteligencia());
        
        //Adicionais
        addStr.setText("+" + personagem.getForcaadd());
        addAgi.setText("+" + personagem.getAgilidadeadd());
        addPer.setText("+" + personagem.getPercepcaoadd());
        addVi.setText("+" + personagem.getVitalidadeadd());
        addInt.setText("+" + personagem.getInteligenciaadd());
        
        Saldo.setText("Coins: " + personagem.getSaldo());
        
        updatebars();
    }
    
 
    
    private void updatebars(){
        //Vida
            int vidaAtual = personagem.getvida();
            int vidaMaxima = personagem.getvidamax();
        
        // Calculando porcentagem
        int porcentagem = (int) ((vidaAtual / (double) vidaMaxima) * 100);
        
        // Atualizando a JProgressBar e JLabel
        Hpbar.setValue(porcentagem);
        Hpbar.setString(vidaAtual + " / " + vidaMaxima + "%"); // Mostra "vidaAtual / vidaMaxima"
        
        //HP
            int manaAtual = personagem.getmana();
            int manaMaxima = personagem.getmanamax();
            
        // Calculando porcentagem
        int porcentagem2 = (int) ((manaAtual / (double) manaMaxima) * 100);
        
        // Atualizando a JProgressBar e JLabel
        Mbbar.setValue(porcentagem2);
        Mbbar.setString(manaAtual + " / " + manaMaxima + "%"); // Mostra "vidaAtual / vidaMaxima"
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        Painel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Conquistas = new javax.swing.JButton();
        Menu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Nomeplayer = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Personagem = new javax.swing.JLabel();
        Mbbar = new javax.swing.JProgressBar();
        Hpbar = new javax.swing.JProgressBar();
        PainelStatus = new javax.swing.JPanel();
        Força = new javax.swing.JLabel();
        Agilidade = new javax.swing.JLabel();
        Percepcao = new javax.swing.JLabel();
        Inteligencia = new javax.swing.JLabel();
        Vitalidade = new javax.swing.JLabel();
        addStr = new javax.swing.JLabel();
        addAgi = new javax.swing.JLabel();
        addPer = new javax.swing.JLabel();
        addInt = new javax.swing.JLabel();
        addVi = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Status = new javax.swing.JButton();
        Skills = new javax.swing.JButton();
        Inventario = new javax.swing.JButton();
        Equip = new javax.swing.JButton();
        Loja = new javax.swing.JButton();
        PainelSaldo = new javax.swing.JPanel();
        Saldo = new javax.swing.JLabel();
        cinco = new javax.swing.JButton();
        quatro = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        dois = new javax.swing.JButton();
        um = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DungeonsLIfe");
        setAlwaysOnTop(true);

        Painel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/gateslife.png"))); // NOI18N

        Conquistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Trofel.png"))); // NOI18N
        Conquistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConquistasActionPerformed(evt);
            }
        });

        Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/55003.png"))); // NOI18N
        Menu.setText("Main");
        Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Menu)
                .addGap(269, 269, 269)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Conquistas, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Conquistas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Menu)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nomeplayer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Nomeplayer.setText("NOME DO JOGADOR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nomeplayer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Nomeplayer)
        );

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Personagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/tt.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Personagem, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 27, Short.MAX_VALUE)
                .addComponent(Personagem))
        );

        Mbbar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        Mbbar.setForeground(new java.awt.Color(0, 204, 204));
        Mbbar.setStringPainted(true);

        Hpbar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        Hpbar.setForeground(new java.awt.Color(255, 0, 51));
        Hpbar.setToolTipText("");
        Hpbar.setStringPainted(true);

        PainelStatus.setBackground(new java.awt.Color(255, 255, 255));
        PainelStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Força.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Força.setForeground(new java.awt.Color(0, 51, 255));
        Força.setText("STR 0");

        Agilidade.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Agilidade.setForeground(new java.awt.Color(0, 51, 255));
        Agilidade.setText("AGI: 0");

        Percepcao.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Percepcao.setForeground(new java.awt.Color(0, 51, 255));
        Percepcao.setText("PER: 0");

        Inteligencia.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Inteligencia.setForeground(new java.awt.Color(0, 51, 255));
        Inteligencia.setText("INT: 0");

        Vitalidade.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Vitalidade.setForeground(new java.awt.Color(0, 51, 255));
        Vitalidade.setText("VIT: 0");

        addStr.setForeground(new java.awt.Color(255, 0, 0));
        addStr.setText("+Str");

        addAgi.setForeground(new java.awt.Color(255, 0, 0));
        addAgi.setText("+Agi");

        addPer.setForeground(new java.awt.Color(255, 0, 0));
        addPer.setText("+Per");

        addInt.setForeground(new java.awt.Color(255, 0, 0));
        addInt.setText("+Int");

        addVi.setForeground(new java.awt.Color(255, 0, 0));
        addVi.setText("+Vit");

        javax.swing.GroupLayout PainelStatusLayout = new javax.swing.GroupLayout(PainelStatus);
        PainelStatus.setLayout(PainelStatusLayout);
        PainelStatusLayout.setHorizontalGroup(
            PainelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelStatusLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PainelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Força)
                    .addComponent(Vitalidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelStatusLayout.createSequentialGroup()
                        .addComponent(addStr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Agilidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addAgi)
                        .addGap(18, 18, 18)
                        .addComponent(Percepcao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addPer))
                    .addGroup(PainelStatusLayout.createSequentialGroup()
                        .addComponent(addVi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Inteligencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addInt)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        PainelStatusLayout.setVerticalGroup(
            PainelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Força)
                    .addComponent(Agilidade)
                    .addComponent(addStr)
                    .addComponent(addAgi)
                    .addGroup(PainelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Percepcao)
                        .addComponent(addPer)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Vitalidade)
                    .addComponent(addVi)
                    .addComponent(Inteligencia)
                    .addComponent(addInt))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HP:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("MB:");

        Status.setBackground(new java.awt.Color(0, 51, 255));
        Status.setForeground(new java.awt.Color(255, 255, 255));
        Status.setText("STATUS");
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        Skills.setBackground(new java.awt.Color(0, 51, 255));
        Skills.setForeground(new java.awt.Color(255, 255, 255));
        Skills.setText("SKILL");
        Skills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkillsActionPerformed(evt);
            }
        });

        Inventario.setBackground(new java.awt.Color(0, 51, 255));
        Inventario.setForeground(new java.awt.Color(255, 255, 255));
        Inventario.setText("INVENTARIO");
        Inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventarioActionPerformed(evt);
            }
        });

        Equip.setBackground(new java.awt.Color(0, 51, 255));
        Equip.setForeground(new java.awt.Color(255, 255, 255));
        Equip.setText("EQUIP");
        Equip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EquipActionPerformed(evt);
            }
        });

        Loja.setBackground(new java.awt.Color(0, 51, 255));
        Loja.setForeground(new java.awt.Color(255, 255, 255));
        Loja.setText("SHOP");
        Loja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LojaActionPerformed(evt);
            }
        });

        PainelSaldo.setBackground(new java.awt.Color(255, 255, 255));
        PainelSaldo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Saldo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Saldo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/s.png"))); // NOI18N
        Saldo.setText("Coins:");

        javax.swing.GroupLayout PainelSaldoLayout = new javax.swing.GroupLayout(PainelSaldo);
        PainelSaldo.setLayout(PainelSaldoLayout);
        PainelSaldoLayout.setHorizontalGroup(
            PainelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSaldoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Saldo)
                .addGap(188, 188, 188))
        );
        PainelSaldoLayout.setVerticalGroup(
            PainelSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelSaldoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Saldo)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Mbbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Hpbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(PainelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Equip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Skills, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Loja, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PainelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Skills, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Equip, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Loja, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PainelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(Hpbar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Mbbar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PainelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Hpbar.getAccessibleContext().setAccessibleName("");

        cinco.setBackground(new java.awt.Color(0, 0, 0));
        cinco.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinco.setForeground(new java.awt.Color(255, 255, 255));
        cinco.setText("5");
        cinco.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cincoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        quatro.setBackground(new java.awt.Color(0, 0, 0));
        quatro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        quatro.setForeground(new java.awt.Color(255, 255, 255));
        quatro.setText("4");
        quatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quatroActionPerformed(evt);
            }
        });

        tres.setBackground(new java.awt.Color(0, 0, 0));
        tres.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tres.setForeground(new java.awt.Color(255, 255, 255));
        tres.setText("3");
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });

        dois.setBackground(new java.awt.Color(0, 0, 0));
        dois.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dois.setForeground(new java.awt.Color(255, 255, 255));
        dois.setText("2");
        dois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doisActionPerformed(evt);
            }
        });

        um.setBackground(new java.awt.Color(0, 0, 0));
        um.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        um.setForeground(new java.awt.Color(255, 255, 255));
        um.setText("1");
        um.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                umActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelLayout = new javax.swing.GroupLayout(Painel);
        Painel.setLayout(PainelLayout);
        PainelLayout.setHorizontalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(um, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dois, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tres, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quatro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cinco, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelLayout.setVerticalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(um, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dois, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tres, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quatro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cinco, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuActionPerformed
        
    }//GEN-LAST:event_MenuActionPerformed

    private void ConquistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConquistasActionPerformed
        
    }//GEN-LAST:event_ConquistasActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        abrirJanelaStatus();
    }//GEN-LAST:event_StatusActionPerformed

    private void doisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doisActionPerformed
        
    }//GEN-LAST:event_doisActionPerformed

    private void SkillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkillsActionPerformed
        abrirJanelaskills();
    }//GEN-LAST:event_SkillsActionPerformed

    private void LojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LojaActionPerformed
       abrirJanelaLoja();
    }//GEN-LAST:event_LojaActionPerformed

    private void InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventarioActionPerformed
        abrirJanelasinve();
    }//GEN-LAST:event_InventarioActionPerformed

    private void EquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EquipActionPerformed
       abrirJanelaequip();
    }//GEN-LAST:event_EquipActionPerformed

    private void umActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_umActionPerformed
        
    }//GEN-LAST:event_umActionPerformed

    private void tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresActionPerformed
        
    }//GEN-LAST:event_tresActionPerformed

    private void quatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quatroActionPerformed
        
    }//GEN-LAST:event_quatroActionPerformed

    private void cincoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cincoAncestorAdded
        
    }//GEN-LAST:event_cincoAncestorAdded

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Agilidade;
    private javax.swing.JButton Conquistas;
    private javax.swing.JButton Equip;
    private javax.swing.JLabel Força;
    private javax.swing.JProgressBar Hpbar;
    private javax.swing.JLabel Inteligencia;
    private javax.swing.JButton Inventario;
    private javax.swing.JButton Loja;
    private javax.swing.JProgressBar Mbbar;
    private javax.swing.JButton Menu;
    private javax.swing.JLabel Nomeplayer;
    private javax.swing.JPanel Painel;
    private javax.swing.JPanel PainelSaldo;
    private javax.swing.JPanel PainelStatus;
    private javax.swing.JLabel Percepcao;
    private javax.swing.JLabel Personagem;
    private javax.swing.JLabel Saldo;
    private javax.swing.JButton Skills;
    private javax.swing.JButton Status;
    private javax.swing.JLabel Vitalidade;
    private javax.swing.JLabel addAgi;
    private javax.swing.JLabel addInt;
    private javax.swing.JLabel addPer;
    private javax.swing.JLabel addStr;
    private javax.swing.JLabel addVi;
    private javax.swing.JButton cinco;
    private javax.swing.JButton dois;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton quatro;
    private javax.swing.JButton tres;
    private javax.swing.JButton um;
    // End of variables declaration//GEN-END:variables
}
