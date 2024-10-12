package DugeonsLife;
import javax.swing.*;

public class Inventario extends javax.swing.JFrame {
    private Personagem personagem;
    private DefaultListModel<String> listModel;

    public Inventario(Personagem personagem) {
        this.personagem = personagem;
        listModel = new DefaultListModel<>();
        
        atualizarInventario();
        initComponents();
        setLocationRelativeTo(null);
    }
    
     private void usarItem() {
    String itemSelecionado = jList1.getSelectedValue(); // Obtém o item selecionado

    if (itemSelecionado != null) {
        // Itens utilizáveis
        if (itemSelecionado.equals("Porção de Cura pequena") || 
            itemSelecionado.equals("Porção de cura Grande") || 
            itemSelecionado.equals("Exilir de Mana") || 
            itemSelecionado.equals("Chave Instantanea Dungeon")) {

            // Usar o item se ele for utilizável
            if (personagem.usarItem(itemSelecionado)) {
                // Verifica qual item foi usado e exibe a mensagem apropriada
                String mensagem;
                if (itemSelecionado.equals("Exilir de Mana")) {
                    mensagem = "Item usado com sucesso! Mana atual: " + personagem.getmana();
                } else if (itemSelecionado.equals("Porção de cura Grande")){
                    mensagem = "Item usado com sucesso! Vida atual: " + personagem.getvida();
                }
                else if (itemSelecionado.equals("Porção de Cura pequena")){
                    mensagem = "Item usado com sucesso! Vida atual: " + personagem.getvida();
                }
                else{
                    mensagem = "Voce usou um item (especial)!";
                }
                JOptionPane.showMessageDialog(this, mensagem, "ALERT", JOptionPane.INFORMATION_MESSAGE);
                atualizarInventario();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível usar o item!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Este item não pode ser usado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um item para usar.", "ALERT", JOptionPane.WARNING_MESSAGE);
    }
}


    private void equiparItem() {
    String itemSelecionado = jList1.getSelectedValue(); // Obtém o item selecionado
    
    if (itemSelecionado != null) {
        // Itens equipáveis
        if (itemSelecionado.equals("Adaga matadora de cavaleiro") || 
            itemSelecionado.equals("Adaga Sombria Rank S") || 
            itemSelecionado.equals("Manopla do rei") || 
            itemSelecionado.equals("Armadura Escama de Dragão") || 
            itemSelecionado.equals("Manto Protetor") || 
            itemSelecionado.equals("Espada Curta de Fogo") || 
            itemSelecionado.equals("Tunica do Caçador") || 
            itemSelecionado.equals("Cajado da Sabedoria")) {
            
            // EiparItembol(itemSelecionado))quipar o item se ele for equipável
            if (equiparItembol(itemSelecionado)) {
                JOptionPane.showMessageDialog(this, "Item equipado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível equipar o item!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Este item não pode ser equipado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um item para equipar.", "ALERT", JOptionPane.WARNING_MESSAGE);
    }
}
    
        // EQUIPAR ITEM
   public boolean equiparItembol(String item) {

       if (personagem.possuiItem(item)) {

            // Verifica se o item já está equipado em qualquer slot
           if (item.equals(personagem.getItemEquipado1()) || 
               item.equals(personagem.getItemEquipado2()) || 
               item.equals(personagem.getItemEquipado3())) {
               JOptionPane.showMessageDialog(this, "Este item já está equipado!", "Erro", JOptionPane.ERROR_MESSAGE);
               return false; // Retorna false se o item já estiver equipado
           }

           // Adaga matadora de cavaleiro
           if (item.equals("Adaga matadora de cavaleiro")) {
               personagem.aumentarForcaadd(5);

               personagem.setequipar(item); // Marca o item como equipado
               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           }
           if(item.equals("Adaga Sombria Rank S")){
              personagem.aumentarForcaadd(10);
              personagem.aumentarAgilidadeadd(10);

              personagem.setequipar(item); // Marca o item como equipado
               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           }
           if(item.equals("Manopla do rei")){
              personagem.aumentarForcaadd(6);

              personagem.setequipar(item); // Marca o item como equipado
               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           }
           if(item.equals("Manto Protetor")){
              personagem.aumentarVitalidadeadd(6);

              personagem.setequipar(item); // Marca o item como equipado
               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           } 
       }
       return false; // Item não encontrado ou não pode ser equipado
   }
   
   private void desequiparItem() {
    String itemSelecionado = jList1.getSelectedValue(); // Obtém o item selecionado

    if (itemSelecionado != null) {
        // Verifica se o item está equipado
        if (personagem.getSlotItem(itemSelecionado) != null) {
            // Desequipa o item
            if (personagem.desequiparItem(itemSelecionado)) {
                JOptionPane.showMessageDialog(this, "Item desequipado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarInventario();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível desequipar o item!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Este item não está equipado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um item para desequipar.", "ALERT", JOptionPane.WARNING_MESSAGE);
    }
}

  
    public boolean desequipar(String item) {
     // Verifica se o item está equipado
     // Adaga matadora de cavaleiro
           if (item.equals("Adaga matadora de cavaleiro")) {
               personagem.reduzirForcaadd(5);

               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           }
           if(item.equals("Adaga Sombria Rank S")){
              personagem.reduzirForcaadd(10);
              personagem.aumentarAgilidadeadd(10);

               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           }
           if(item.equals("Manopla do rei")){
              personagem.reduzirForcaadd(6);

               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           }
           if(item.equals("Manto Protetor")){
              personagem.reduzirVitalidadeadd(6);

               // Adicionar ao slot de equipamento (se aplicável)
               return true;
           } 
     return false; // Item não estava equipado
 }
   
    //Sistemns
     private void venderItem() {
        String itemSelecionado = jList1.getSelectedValue(); // Obtém o item selecionado
    if (itemSelecionado != null) {
        Loja loja = new Loja(personagem);
        
        // Obtemos o preço fixo ou calculado para o item
        double valorRecebido = loja.getPrecoItem(itemSelecionado) * 0.5; // Retorna 50% do preço
        
        // Vende o item
        if (loja.venderItem(itemSelecionado)) {
            personagem.setSaldo(personagem.getSaldo() + valorRecebido); // Atualiza o saldo
            JOptionPane.showMessageDialog(this, "Item vendido com sucesso!\nSaldo atualizado: " + personagem.getSaldo(), "ALERT", 
                JOptionPane.INFORMATION_MESSAGE);
            atualizarInventario(); // Atualiza a lista após a venda
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao vender item!", "ALERT", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um item para vender.", "ALERT", 
                JOptionPane.INFORMATION_MESSAGE);
    }
     }
     
       
     private void atualizarInventario() {
        // Limpa a lista atual e adiciona os itens do inventário
        listModel.clear();
        for (String item : personagem.getInventario()) {
            listModel.addElement(item);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>(listModel);
        Usar = new javax.swing.JButton();
        Equipar = new javax.swing.JButton();
        vender = new javax.swing.JButton();
        Inventario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Desequipar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario");
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jList1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        Usar.setText("USAR");
        Usar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsarActionPerformed(evt);
            }
        });

        Equipar.setText("EQUIPAR");
        Equipar.setToolTipText("");
        Equipar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EquiparActionPerformed(evt);
            }
        });

        vender.setText("SELL");
        vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venderActionPerformed(evt);
            }
        });

        Inventario.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Inventario.setForeground(new java.awt.Color(255, 255, 255));
        Inventario.setText("INVENTARIO");

        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Ao vender e retornado apenas 50% do valor investido");

        Desequipar.setText("DESEQUIPAR");
        Desequipar.setToolTipText("");
        Desequipar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesequiparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Inventario)
                                .addGap(129, 129, 129))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(99, 99, 99))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Usar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Equipar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Desequipar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vender)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Inventario)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vender)
                    .addComponent(Equipar)
                    .addComponent(Usar)
                    .addComponent(Desequipar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsarActionPerformed
        usarItem();
    }//GEN-LAST:event_UsarActionPerformed

    private void EquiparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EquiparActionPerformed
        equiparItem();
    }//GEN-LAST:event_EquiparActionPerformed

    private void venderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venderActionPerformed
        venderItem();
    }//GEN-LAST:event_venderActionPerformed

    private void DesequiparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesequiparActionPerformed
        desequiparItem();
    }//GEN-LAST:event_DesequiparActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Desequipar;
    private javax.swing.JButton Equipar;
    private javax.swing.JLabel Inventario;
    private javax.swing.JButton Usar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton vender;
    // End of variables declaration//GEN-END:variables
}
