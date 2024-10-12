package DugeonsLife;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Personagem implements Serializable{
    private String nome;
    
    //Atributos Principais
    private int forca;
    private int agilidade;
    private int percepcao;
    private int vitalidade;
    private int inteligencia;
    private int resistencia;
    
    //Atributos Adicionais
    private int forcaadicional;
    private int agilidadeadicional;
    private int percepcaoadicional;
    private int vitalidadeadicional;
    private int inteligenciaadicional;
    private int resistenciaadicional;

    private int pontosHabilidade;
    
    //Momentaneos
    private int vida;
    private int vidamax;
    
    private int mana;
    private int manamax;
   
    private int level;
    private int levelClasse;
    
    //Outros Atributos
    private int adicionais;
    private double saldo;
    private String classe;
    private String titulo;
    
    private List<String> inventario;
    private List<String> conquistas;

    private String slot1;
    private String slot2;
    private String slot3;
    
    //SKILLS
    private List<String> skillsPassivas;
    private List<String> descricoesPassivas;
    
    private List<String> skillsAtivas;
    private List<String> descricoesAtivas;
    
    private List<String> skillsClasse;
    private List<String> descricoesClasse;

    
    public Personagem(String nome) {
        this.nome = nome;
        this.saldo = 100000;
        
        this.level = 10;
        this.levelClasse = 0;
        this.classe = "E";
        this.titulo = "Sem titulo";
        
        this.pontosHabilidade = 1;
        
        //Atributos
        this.nome = nome;
        this.forca = 1;
        this.agilidade = 1;
        this.percepcao = 1;
        this.vitalidade = 1;
        this.inteligencia = 1;
        this.resistencia = 1;
        
        //Atributos Adicionais
        this.forcaadicional = 0;
        this.agilidadeadicional = 0;
        this.percepcaoadicional = 0;
        this.vitalidadeadicional = 0;
        this.inteligenciaadicional = 0;
        this.resistenciaadicional = 0;
        
        //SLOTS EQUIPAR
        this.slot1 = null;
        this.slot2 = null;
        this.slot3 = null;
        
        //Momentaneos
        this.vida = 10;
        this.vidamax = 100;
        
        this.mana = 2;
        this.manamax = 10;
        
        //Arrays
        this.inventario = new ArrayList<>();
        this.conquistas = new ArrayList<>();

        //Passivas Skills
        this.skillsPassivas = new ArrayList<>();
        this.descricoesPassivas = new ArrayList<>();
        
        //Ativas Skills
        this.skillsAtivas = new ArrayList<>();
        this.descricoesAtivas = new ArrayList<>();
 
        //Classe Skills
        this.skillsClasse = new ArrayList<>();
        this.descricoesClasse = new ArrayList<>();
     }
    
     
     public void setequipar(String item) {
      if (slot1 == null) {
            slot1 = item;
        } else if (slot2 == null) {
            slot2 = item;
        } else if (slot3 == null) {
            slot3 = item;
        } else {
            removerBonusItem(slot1);  // Remove os bônus do item que será desequipado
            slot1 = slot2;  // Move os itens para cima
            slot2 = slot3;
            slot3 = item;  // Equipa o novo item
        }
    }
     
     public void removerBonusItem(String itemEquipado) {
        if (itemEquipado.equals("Adaga matadora de cavaleiro")) {
            reduzirForcaadd(5);  // Remove o bônus de força
        }
        if (itemEquipado.equals("Adaga Sombria Rank S")) {
            reduzirForcaadd(10);  // Remove o bônus de força
            reduzirAgilidadeadd(10); // Remove o bônus de agilidade
        }
        if (itemEquipado.equals("Manopla do rei")) {
            reduzirForcaadd(6);  // Remove o bônus de força
        }
        if (itemEquipado.equals("Manto Protetor")) {
            reduzirVitalidadeadd(6);  // Remove o bônus de vitalidade
        }
    }
     
     //ITENS EQUIPÀDOS GETTERS
      public String getItemEquipado1() {
        return slot1;
    }

    public String getItemEquipado2() {
        return slot2;
    }

    public String getItemEquipado3() {
        return slot3;
    }
    
     public String getSlotItem(String item) {
        if (item.equals(slot1)) {
            return "slot1";
        } else if (item.equals(slot2)) {
            return "slot2";
        } else if (item.equals(slot3)) {
            return "slot3";
        }
        return null; // O item não está equipado
    }
    
     public boolean desequiparItem(String item) {
        String slot = getSlotItem(item);
        if (slot != null) {
            // Remove o item do slot apropriado
            switch (slot) {
                case "slot1":
                    slot1 = null;
                    break;
                case "slot2":
                    slot2 = slot1; // Mover slot1 para slot2
                    slot1 = null;
                    break;
                case "slot3":
                    slot3 = slot2; // Mover slot2 para slot3
                    slot2 = null;
                    break;
            }
            // Aqui você pode adicionar a lógica para remover bônus do item
            removerBonusItem(item); // Você deve implementar esta lógica
            return true;
        }
        return false; // O item não estava equipado
    }
    
     
    // USAR ITEM
    public boolean usarItem(String item) {
        if (inventario.contains(item)) {
            // Lógica para usar o item
            // Porção de Cura pequena
            if (item.equals("Porção de Cura pequena")) {
                vida += 30; // Restaura 30 de vida
                if (vida > vidamax) {
                    vida = vidamax; // Não ultrapassar o máximo
                }
                
                inventario.remove(item); // Remove do inventário após uso
                return true;
            }
            // Porção de cura Grande
            if (item.equals("Porção de cura Grande")) {
                vida += 70; // Restaura 70 de vida
                if (vida > vidamax) {
                    vida = vidamax; // Não ultrapassar o máximo
                }
                inventario.remove(item);
                return true;
            }
            // Elixir de Mana
            if (item.equals("Exilir de Mana")) {
                mana += 50; // Restaura 50 de mana
                if (mana > manamax) {
                    mana = manamax; // Não ultrapassar o máximo
                }
                inventario.remove(item);
                return true;
            }
            // Chave Instantânea Dungeon (exemplo de lógica personalizada para esse item)
            if (item.equals("Chave Instantanea Dungeon")) {
                // Chave é usada para desbloquear uma dungeon, ou talvez salvar um progresso especial
                inventario.remove(item); // Remove a chave após uso
                // Implementar a lógica da dungeon aqui, se aplicável
                return true;
            }
        }
        return false; // Item não encontrado ou não pode ser usado
    }
    
 
    public String getnome() {
        return nome;
    }

    public boolean possuiItem(String item) {
        return inventario.contains(item);
    }
    
    
     //SKILLS
    public void adicionarSkillPassiva(String nomeSkill, String descricaoSkill) {
        skillsPassivas.add(nomeSkill); // Adiciona a skill à lista
        descricoesPassivas.add(descricaoSkill);
    }
    
    public String getDescricaoSkillPassiva(int index) {
        if (index >= 0 && index < descricoesPassivas.size()) {
            return descricoesPassivas.get(index);
        }
        return "Descrição não disponível.";
    }
    
    public List<String> getSkillsPassivas() {
        return skillsPassivas;
    }
    
    
    // Adiciona um item ao inventário
    public void adicionarAoInventario(String item) {
        inventario.add(item);
    }
    
    public boolean removerItem(String item) {
        return inventario.remove(item); // Retorna true se o item foi removido com sucesso
    }
    
    // Retorna o inventário do personagem
    public List<String> getInventario() {
        return inventario;
    }
    
    // Verifica se o personagem tem saldo suficiente para comprar um item
    public boolean temSaldoSuficiente(double valor) {
        return saldo >= valor;
    }

    // Deduz o valor do saldo do personagem após a compra
    public void deduzirSaldo(double valor) {
        saldo -= valor;
    }
    
    
    // ATRIBUTOS PRINCIPAIS AUMENTAR
    public void aumentarForca(int valor) {
        this.forca += valor;
    }

    public void aumentarAgilidade(int valor) {
        this.agilidade += valor;
    }

    public void aumentarPercepcao(int valor) {
        this.percepcao += valor;
    }

    public void aumentarVitalidade(int valor) {
        this.vitalidade += valor;
    }

    public void aumentarInteligencia(int valor) {
        this.inteligencia += valor;
    }
    
    
    
    // ATRIBUTOS ADICIONAIS AUMENTAR
    public void aumentarForcaadd(int valor) {
        this.forcaadicional += valor;
    }

    public void aumentarAgilidadeadd(int valor) {
        this.agilidadeadicional += valor;
    }

    public void aumentarPercepcaoadd(int valor) {
        this.percepcaoadicional += valor;
    }

    public void aumentarVitalidadeadd(int valor) {
        this.vitalidadeadicional += valor;
    }

    public void aumentarInteligenciaadd(int valor) {
        this.inteligenciaadicional += valor;
    }
    
    
    // ATRIBUTOS ADICIONAIS REDUZIR
    public void reduzirForcaadd(int valor) {
        this.forcaadicional -= valor;
    }

    public void reduzirAgilidadeadd(int valor) {
        this.agilidadeadicional -= valor;
    }

    public void reduzirPercepcaoadd(int valor) {
        this.percepcaoadicional -= valor;
    }

    public void reduzirVitalidadeadd(int valor) {
        this.vitalidadeadicional -= valor;
    }

    public void reduzirInteligenciaadd(int valor) {
        this.inteligenciaadicional -= valor;
    }
    
    
    //Gets vida e mana
    public int getvida() {
        return vida;
    }
    public int getvidamax() {
        return vidamax;
    }
    
    public int getmana() {
        return mana;
    }
    public int getmanamax() {
        return manamax;
    }
    
    // Métodos para atualizar a vida
    public void setVidaAtual(int vidaAtual) {
        this.vida = vidaAtual;
    }
    
  
    //Gets leveis
    public int getlevel() {
        return level;
    }
    public int getlevelclasse() {
        return levelClasse;
    }
    
    //gets mais
    public String getClasse() {
        return classe;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    // Método para definir o saldo
    public void setSaldo(double novoSaldo) {
        this.saldo = novoSaldo;
    }
    
    
    //Atributos fisicos
    public int getForca() {
        return forca;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getPercepcao() {
        return percepcao;
    }

    public int getVitalidade() {
        return vitalidade;
    }

    public int getInteligencia() {
        return inteligencia;
    }
    
    //Atributos Fisicos Adicionais
     public int getForcaadd() {
        return forcaadicional;
    }

    public int getAgilidadeadd() {
        return agilidadeadicional;
    }

    public int getPercepcaoadd() {
        return percepcaoadicional;
    }

    public int getVitalidadeadd() {
        return vitalidadeadicional;
    }

    public int getInteligenciaadd() {
        return inteligenciaadicional;
    }
    
    //pontos hab
    public int getPontosHabilidade() {
        return pontosHabilidade;
    }

    public void adicionarPontosHabilidade(int pontos) {
        this.pontosHabilidade += pontos;
    }
    
}
