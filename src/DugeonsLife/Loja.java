package DugeonsLife;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Loja extends javax.swing.JFrame {
    private Personagem personagem;
    private DefaultListModel<String> listaItensModel;  // Model para o JList
    private Map<String, Double> itensDisponiveis;
    private Map<String, String> itensInformacoes; // Mapa para informações dos itens
    
    private List<String> todosItens; // Lista de todos os itens disponíveis
    private final int numeroItensExibidos = 12; // Número de itens a serem exibidos
    
    public Loja(Personagem personagem) {
        this.personagem = personagem;
        listaItensModel = new DefaultListModel<>();
        itensDisponiveis = new HashMap<>();
        todosItens = new ArrayList<>(); // Inicializa a lista de itens
        inicializarItens(); // Preenche a lista de itens

        initComponents();
        setLocationRelativeTo(null);
        atualizarItensDisponiveis(); // Atualiza os itens disponíveis
        
        // Adiciona um listener à lista para mostrar informações do item selecionado
        listaItensLojas.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                atualizarInformacoesDoItem();
            }
        });
    }
    
    private void inicializarItens() {
        itensInformacoes = new HashMap<>();
        
        // Adiciona todos os itens disponíveis à lista com preços predefinidos
        adicionarItem("Adaga matadora de cavaleiro", 500.0, "Eficiente contra armaduras ao lutar contra cavaleiros. Aumenta a força em +5.");
        adicionarItem("Adaga Sombria Rank S", 1000.0, "Um artefato lendário que aumenta a velocidade de ataque em +13.");
        adicionarItem("Manopla do rei", 50.0, "Aumenta a resistência em +6.");
        adicionarItem("Armadura Escama de Dragão", 100.0, "Oferece proteção elevada e resistência a fogo.");
        adicionarItem("Manto Protetor", 60.0, "Aumenta a defesa em +3 e oferece camuflagem.");
        adicionarItem("Porção de Cura pequena", 50.0, "Restaura 30% da vida do usuário.");
        adicionarItem("Exilir de Mana", 200.0, "Restaura 50% de mana.");
        adicionarItem("Chave Instantanea Dungeon", 5000.0, "Permite acesso imediato a qualquer dungeon.");
        adicionarItem("Orb da avareza", 1000.0, "Aumenta a quantidade de loot em 20%.");
        adicionarItem("Cajado da Sabedoria", 600.0, "Aumenta o poder mágico em 15%.");
        adicionarItem("Espada Curta de Fogo", 100.0, "Causa dano adicional de fogo.");
        adicionarItem("Tunica do Caçador", 800.0, "Aumenta a habilidade de rastreamento.");
        adicionarItem("Porção de cura Grande", 200.0, "Restaura 50% da vida do usuário.");

        // Adiciona todos os itens à lista
        todosItens.addAll(itensDisponiveis.keySet());
        Collections.shuffle(todosItens); // Embaralha os itens   
    }
    
    // Adiciona um item à loja com preço e descrição
    private void adicionarItem(String nome, double preco, String descricao) {
        itensDisponiveis.put(nome, preco);
        itensInformacoes.put(nome, descricao);
    }
    
    private void abrirJanelaStatus() {
        Inventario Inv = new Inventario(personagem);

        Inv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Isso garante que apenas a janela Status feche
        Inv.setVisible(true);
    }
    
     private void atualizarInformacoesDoItem() {
        String itemSelecionado = listaItensLojas.getSelectedValue();
    if (itemSelecionado != null) {
        String[] partes = itemSelecionado.split(" - ");
        String nomeItem = partes[0];
        
        // Verifica se o nome do item está no mapa de informações
        String info = itensInformacoes.get(nomeItem);
        if (info != null) {
            Informações.setText(info); // Exibe a informação do item
        } else {
            Informações.setText("Informação não disponível."); // Mensagem alternativa
        }
    } else {
        Informações.setText(""); // Limpa as informações se nenhum item for selecionado
    }
    }
    
    private void comprarItem(){
       // Implementar lógica de compra
        String itemSelecionado = listaItensLojas.getSelectedValue();
        if (itemSelecionado != null) {
            String[] partes = itemSelecionado.split(" - ");
            String Item = partes[0];
            Double precoItem = itensDisponiveis.get(Item);

            if (personagem.getSaldo() >= precoItem) {
                personagem.setSaldo(personagem.getSaldo() - precoItem);
                
                // Adiciona o item ao inventário do personagem
                personagem.adicionarAoInventario(Item);
                JOptionPane.showMessageDialog(this, "Você comprou " + Item, "ALERT", 
                JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Moedas insuficientes!", "ALERT", 
                JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para comprar!", "ALERT", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public double getPrecoItem(String item) {
        return 100.0; // Preço fixo para todos os itens
    }
    
    public boolean venderItem(String item) {
        // Verifica se o personagem possui o item
        if (personagem.getInventario().contains(item)) {
            // Remove o item do inventário do personagem
            personagem.removerItem(item);
            return true; // Venda bem-sucedida
        } else {
            return false; // Item não encontrado no inventário
        }
    }
    
    private void atualizarItensDisponiveis(){
        listaItensModel.clear();
        int itensParaExibir = Math.min(numeroItensExibidos, todosItens.size());
        for (int i = 0; i < itensParaExibir; i++) {
            String item = todosItens.get(i);
            listaItensModel.addElement(item + " - R$: " + itensDisponiveis.get(item));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Shop = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaItensLojas = new javax.swing.JList<>(listaItensModel);
        jScrollPane1 = new javax.swing.JScrollPane();
        Informações = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Buy1 = new javax.swing.JButton();
        Inventory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loja");
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        Shop.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Shop.setForeground(new java.awt.Color(255, 255, 255));
        Shop.setText("SHOP");

        listaItensLojas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        listaItensLojas.setToolTipText("");
        jScrollPane2.setViewportView(listaItensLojas);

        Informações.setEditable(false);
        Informações.setColumns(20);
        Informações.setRows(5);
        jScrollPane1.setViewportView(Informações);

        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Os itens sempre atualizam...");

        Buy1.setText("BUY");
        Buy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buy1ActionPerformed(evt);
            }
        });

        Inventory.setText("Inventory");
        Inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(Buy1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Shop)
                        .addGap(147, 147, 147))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(Shop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Buy1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Buy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buy1ActionPerformed
        comprarItem();
    }//GEN-LAST:event_Buy1ActionPerformed

    private void InventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventoryActionPerformed
        abrirJanelaStatus();
    }//GEN-LAST:event_InventoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buy1;
    private javax.swing.JTextArea Informações;
    private javax.swing.JButton Inventory;
    private javax.swing.JLabel Shop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaItensLojas;
    // End of variables declaration//GEN-END:variables
}
