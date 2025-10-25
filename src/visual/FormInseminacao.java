/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;
import modelo.Cliente;
import modelo.DAOCliente;
import modelo.DAOInseminacao;
import modelo.DAOLocal;
import modelo.Inseminacao;
import modelo.Local;

/**
 *
 * @author warme
 */
public class FormInseminacao extends javax.swing.JDialog {
    DAOInseminacao daoInseminacao = new DAOInseminacao();
    DAOCliente daoCliente = new DAOCliente();
    
    public void atualizaTabela(){
        listInseminacao.clear();
        listInseminacao.addAll(daoInseminacao.getLista());
        int linha = listInseminacao.size()-1;
        if (linha>=0){
            tabelaInseminacao.setRowSelectionInterval(linha, linha);
            tabelaInseminacao.scrollRectToVisible(tabelaInseminacao.getCellRect(linha, linha, true));
        }
    }

    private void trataEdicao(boolean editando){
        botaoCancelar.setEnabled(editando);
        botaoSalvar.setEnabled(editando);
        botaoEditar.setEnabled(!editando);
        int linha = listInseminacao.size() -1;
        if(linha<0){
            botaoExcluir.setEnabled(false);
            textoValorMaoObra.setText("");
            textoCodigo.setText("");
            textoValorSemen.setText("");
            textoValorTotal.setText("");
            textoDataInseminacao.setText("");
            textoHora.setText("");
        }
        else{
            botaoExcluir.setEnabled(!editando);
        }
        botaoNovo.setEnabled(!editando);
        botaoAnterior.setEnabled(!editando);
        botaoFechar.setEnabled(!editando);
        botaoPrimeiro.setEnabled(!editando);
        botaoProximo.setEnabled(!editando);
        botaoUltimo.setEnabled(!editando); 
        textoCodigo.setEnabled(editando);
        textoValorMaoObra.setEnabled(editando);
        textoValorSemen.setEnabled(editando);
        textoValorTotal.setEnabled(editando);
        textoDataInseminacao.setEnabled(editando);
        textoQuantidade.setEnabled(editando);
        cbxRacaSemen.setEnabled(editando);     
        cbxTipoInseminacao.setEnabled(editando);
        cbxCliente.setEnabled(editando);
        textoHora.setEnabled(editando);
        tabelaInseminacao.setEnabled(editando);        
    }
    
    public boolean validaCampos(){
        
        if (!(cbxCliente.getSelectedIndex()>=0)){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme o cliente correspondente.");
            cbxCliente.requestFocus();
            return false;
        }
        
        
        
        if (!(textoDataInseminacao.getText().length()>0)){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme a data da inseminação.");
            textoDataInseminacao.requestFocus();
            return false;
        }
        
        if (textoDataInseminacao.getText().length()>0){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try{
                sdf.parse(textoDataInseminacao.getText());
            }catch (Exception erro){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme uma data válida.");
            textoDataInseminacao.requestFocus();
            return false;
            }
        }
        
        
        
        if (!(textoHora.getText().length()>0)){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme um horário para a inseminação.");
            textoHora.requestFocus();
            return false;
        }
        
        if (textoHora.getText().length()>0){
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
            sdf2.setLenient(false);
            try{
                sdf2.parse(textoHora.getText());
            }catch (Exception erro){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme uma horário válido.");
            textoHora.requestFocus();
            return false;
            }
        }
        
        if (!(cbxRacaSemen.getSelectedIndex()>=0)){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme a raça do sêmen.");
            cbxRacaSemen.requestFocus();
            return false;
        }
        
        if (!(cbxTipoInseminacao.getSelectedIndex()>=0)){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme o tipo da inseminação.");
            cbxTipoInseminacao.requestFocus();
            return false;
        }
        
        
        
        
        if (!(textoQuantidade.getText().length() > 0)) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme a quantidade do sêmen.");
            textoQuantidade.requestFocus();
            return false;
        }
        if (!(textoValorSemen.getText().length() > 0)) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme o valor do sêmen.");
            textoValorSemen.requestFocus();
            return false;
        }

        if (!(textoValorMaoObra.getText().length() > 0)) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme o valor da mão de obra.");
            textoValorMaoObra.requestFocus();
            return false;
        }

       
          
            if(textoQuantidade.getText().length()>0){
             try {
                Integer.parseInt(textoQuantidade.getText());
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme um número inteiro para a quantidade do sêmen.");
                textoQuantidade.requestFocus();
                return false;

            }   
            }
          
            if(textoValorSemen.getText().length()>0){
             try {
                Double.parseDouble(textoValorSemen.getText());
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme um valor válido para o preço do sêmen.");
                textoValorSemen.requestFocus();
                return false;

            }   
            }
            
            if(textoValorMaoObra.getText().length()>0){
             try {
                Double.parseDouble(textoValorMaoObra.getText());
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme um valor válido para a mão de obra.");
                textoValorMaoObra.requestFocus();
                return false;

            }   
            }
      
        
        /*if (!(textoValorTotal.getText().length()>0)){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme o valor total.");
            textoValorTotal.requestFocus();
            return false;
        }

        if (textoValorTotal.getText().length()>0){
            try{
                Double.parseDouble(textoValorTotal.getText());
            }catch (Exception erro){
            JOptionPane.showMessageDialog(null, "ATENÇÃO\nInforme um valor válido para o preço final.");
            textoValorTotal.requestFocus();
            return false;
            }
        }*/
        
        return true;
    }
    
    /**
     * Creates new form FormInseminacao
     */
    public FormInseminacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        atualizaTabela();
        trataEdicao(false);
        listCliente.clear();
        listCliente.addAll(daoCliente.getLista());
        listInseminacao.clear();
        listInseminacao.addAll(daoInseminacao.getLista());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listInseminacao = org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList <Inseminacao>())
        ;
        listCliente = org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList <Cliente>())
        ;
        converteData = new modelo.ConverteData();
        painelNavegacao = new javax.swing.JPanel();
        botaoPrimeiro = new javax.swing.JButton();
        botaoAnterior = new javax.swing.JButton();
        botaoProximo = new javax.swing.JButton();
        botaoUltimo = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        painelAbas = new javax.swing.JTabbedPane();
        abaLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaInseminacao = new javax.swing.JTable();
        abaCadastro = new javax.swing.JPanel();
        painelAcoes = new javax.swing.JPanel();
        botaoNovo = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textoCodigo = new javax.swing.JTextField();
        cbxTipoInseminacao = new javax.swing.JComboBox<>();
        cbxRacaSemen = new javax.swing.JComboBox<>();
        textoQuantidade = new javax.swing.JTextField();
        javax.swing.text.MaskFormatter maskData = null;
        try{
            maskData = new javax.swing.text.MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
        }catch (Exception e){
            System.out.println("Erro de máscara "+e);
        }
        textoDataInseminacao = new javax.swing.JFormattedTextField(maskData);
        jLabel9 = new javax.swing.JLabel();
        cbxCliente = new javax.swing.JComboBox<>();
        textoValorTotal = new javax.swing.JTextField();
        textoValorMaoObra = new javax.swing.JTextField();
        textoValorSemen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        javax.swing.text.MaskFormatter maskHora = null;
        try{
            maskHora = new javax.swing.text.MaskFormatter("##:##:##");
            maskHora.setPlaceholderCharacter('_');
        }catch (Exception e){
            System.out.println("Erro de máscara "+e);
        }
        textoHora = new javax.swing.JFormattedTextField(maskHora);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Inseminações");

        painelNavegacao.setBackground(new java.awt.Color(0, 102, 51));
        painelNavegacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navegação", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(250, 250, 250))); // NOI18N
        painelNavegacao.setForeground(new java.awt.Color(250, 250, 250));
        painelNavegacao.setLayout(new java.awt.GridLayout(1, 0));

        botaoPrimeiro.setBackground(new java.awt.Color(250, 250, 250));
        botaoPrimeiro.setText("Primeiro");
        botaoPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPrimeiroActionPerformed(evt);
            }
        });
        painelNavegacao.add(botaoPrimeiro);

        botaoAnterior.setBackground(new java.awt.Color(250, 250, 250));
        botaoAnterior.setText("Anterior");
        botaoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAnteriorActionPerformed(evt);
            }
        });
        painelNavegacao.add(botaoAnterior);

        botaoProximo.setBackground(new java.awt.Color(250, 250, 250));
        botaoProximo.setText("Próximo");
        botaoProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProximoActionPerformed(evt);
            }
        });
        painelNavegacao.add(botaoProximo);

        botaoUltimo.setBackground(new java.awt.Color(250, 250, 250));
        botaoUltimo.setText("Último");
        botaoUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoUltimoActionPerformed(evt);
            }
        });
        painelNavegacao.add(botaoUltimo);

        botaoFechar.setBackground(new java.awt.Color(250, 250, 250));
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });
        painelNavegacao.add(botaoFechar);

        painelAbas.setBackground(new java.awt.Color(204, 255, 153));

        abaLista.setLayout(new java.awt.BorderLayout());

        tabelaInseminacao.setBackground(new java.awt.Color(204, 255, 153));

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listInseminacao, tabelaInseminacao);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Código");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cliente}"));
        columnBinding.setColumnName("Cliente");
        columnBinding.setColumnClass(modelo.Cliente.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${raca}"));
        columnBinding.setColumnName("Raça");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipo}"));
        columnBinding.setColumnName("Tipo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${quantidade}"));
        columnBinding.setColumnName("Quantidade");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataFormatado}"));
        columnBinding.setColumnName("Data");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hora}"));
        columnBinding.setColumnName("Hora");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${precoTotal}"));
        columnBinding.setColumnName("Preço Total");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tabelaInseminacao);

        abaLista.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        painelAbas.addTab("Lista", abaLista);

        abaCadastro.setBackground(new java.awt.Color(204, 255, 153));

        painelAcoes.setBackground(new java.awt.Color(0, 102, 51));
        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(250, 250, 250))); // NOI18N
        painelAcoes.setForeground(new java.awt.Color(250, 250, 250));
        painelAcoes.setLayout(new java.awt.GridLayout(1, 0));

        botaoNovo.setBackground(new java.awt.Color(250, 250, 250));
        botaoNovo.setText("Novo");
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });
        painelAcoes.add(botaoNovo);

        botaoEditar.setBackground(new java.awt.Color(250, 250, 250));
        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });
        painelAcoes.add(botaoEditar);

        botaoCancelar.setBackground(new java.awt.Color(250, 250, 250));
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });
        painelAcoes.add(botaoCancelar);

        botaoSalvar.setBackground(new java.awt.Color(250, 250, 250));
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });
        painelAcoes.add(botaoSalvar);

        botaoExcluir.setBackground(new java.awt.Color(250, 250, 250));
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });
        painelAcoes.add(botaoExcluir);

        jLabel1.setText("Código:");

        jLabel2.setText("Data da Inseminação:");

        jLabel3.setText("Tipo da Inseminação:");

        jLabel4.setText("Raça do Sêmen:");

        jLabel5.setText("Quantidade do Sêmen:");

        jLabel6.setText("Valor de Cada Sêmen:");

        jLabel7.setText("Valor da Mão de Obra por Sêmen:");

        jLabel8.setText("Valor Total a Pagar:");

        textoCodigo.setEditable(false);
        textoCodigo.setBackground(new java.awt.Color(250, 250, 250));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.codigo}"), textoCodigo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        textoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoCodigoActionPerformed(evt);
            }
        });

        cbxTipoInseminacao.setBackground(new java.awt.Color(250, 250, 250));
        cbxTipoInseminacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Convencional", "Sexado - Fêmea", "Sexado - Macho" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tipo}"), cbxTipoInseminacao, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cbxRacaSemen.setBackground(new java.awt.Color(250, 250, 250));
        cbxRacaSemen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Girolando", "Guzerá", "Nelore", "Gir Leiteiro", "Gir de Corte", "Holandês", "Canchim", "Gerson", "Angus", "Tabapuã", "Simental", "Brahma", "Caracu", "Jersey", "Criolo Lageano", "Charolês" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.raca}"), cbxRacaSemen, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cbxRacaSemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRacaSemenActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.quantidade}"), textoQuantidade, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        textoQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoQuantidadeFocusLost(evt);
            }
        });
        textoQuantidade.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                textoQuantidadeInputMethodTextChanged(evt);
            }
        });
        textoQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoQuantidadeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoQuantidadeKeyTyped(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.data}"), textoDataInseminacao, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setConverter(converteData);
        bindingGroup.addBinding(binding);

        textoDataInseminacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDataInseminacaoActionPerformed(evt);
            }
        });

        jLabel9.setText("Cliente:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listCliente, cbxCliente);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.cliente}"), cbxCliente, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        textoValorTotal.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.precoTotal}"), textoValorTotal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        textoValorTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoValorTotalFocusLost(evt);
            }
        });
        textoValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoValorTotalActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.maoObra}"), textoValorMaoObra, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        textoValorMaoObra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textoValorMaoObraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoValorMaoObraFocusLost(evt);
            }
        });
        textoValorMaoObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoValorMaoObraActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.valorSemen}"), textoValorSemen, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        textoValorSemen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textoValorSemenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoValorSemenFocusLost(evt);
            }
        });
        textoValorSemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoValorSemenActionPerformed(evt);
            }
        });

        jLabel10.setText("Horário:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tabelaInseminacao, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.hora}"), textoHora, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout abaCadastroLayout = new javax.swing.GroupLayout(abaCadastro);
        abaCadastro.setLayout(abaCadastroLayout);
        abaCadastroLayout.setHorizontalGroup(
            abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelAcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
            .addGroup(abaCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoValorTotal))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoHora))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxRacaSemen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoInseminacao, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoDataInseminacao))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoValorMaoObra))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoValorSemen))
                    .addGroup(abaCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        abaCadastroLayout.setVerticalGroup(
            abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCadastroLayout.createSequentialGroup()
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoDataInseminacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(textoHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxRacaSemen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxTipoInseminacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textoValorSemen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textoValorMaoObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        painelAbas.addTab("Cadastro", abaCadastro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelNavegacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelAbas)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelAbas, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void textoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoCodigoActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        // TODO add your handling code here:
        listInseminacao.add((Inseminacao) new Inseminacao());
        int linha = listInseminacao.size()-1;
        tabelaInseminacao.setRowSelectionInterval(linha, linha);
//qual texto ganha o foco?
        cbxCliente.requestFocus();
        trataEdicao(true);
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // TODO add your handling code here:
        if (validaCampos()){
        int linhaSelecionada = tabelaInseminacao.getSelectedRow();
        Inseminacao objInseminacao = listInseminacao.get(linhaSelecionada);
        daoInseminacao.salvar(objInseminacao);
        atualizaTabela();
        trataEdicao(false);
        }
        
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        // TODO add your handling code here:
        trataEdicao(false);
        atualizaTabela();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        // TODO add your handling code here:
        trataEdicao(true);
        textoDataInseminacao.requestFocus();
    }//GEN-LAST:event_botaoEditarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        // TODO add your handling code here:
        int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Não"}, "Sim");
        if(opcao==0){
            int linhaSelecionada = tabelaInseminacao.getSelectedRow();
            Inseminacao objInseminacao = listInseminacao.get(linhaSelecionada);
            daoInseminacao.remover(objInseminacao);
            atualizaTabela();
            trataEdicao(false);
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPrimeiroActionPerformed
        // TODO add your handling code here:
        tabelaInseminacao.setRowSelectionInterval(0, 0);
        tabelaInseminacao.scrollRectToVisible(tabelaInseminacao.getCellRect(0,0,true));

    }//GEN-LAST:event_botaoPrimeiroActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        // TODO add your handling code here:
        int linha = tabelaInseminacao.getSelectedRow();
        if ((linha-1)>=0){
            linha--;
            }
        tabelaInseminacao.setRowSelectionInterval(linha, linha);
        tabelaInseminacao.scrollRectToVisible(tabelaInseminacao.getCellRect(linha, 0, true));

    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        // TODO add your handling code here:
        int linha = tabelaInseminacao.getSelectedRow();
        if ((linha+1)<=tabelaInseminacao.getRowCount() -1){
            linha++;
        }
        tabelaInseminacao.setRowSelectionInterval(linha, linha);
        tabelaInseminacao.scrollRectToVisible(tabelaInseminacao.getCellRect(linha, 0, true));
    }//GEN-LAST:event_botaoProximoActionPerformed

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUltimoActionPerformed
        // TODO add your handling code here:
        int linha = tabelaInseminacao.getRowCount()-1;
        
        tabelaInseminacao.setRowSelectionInterval(linha, linha);
        tabelaInseminacao.scrollRectToVisible(tabelaInseminacao.getCellRect(linha, 0, true));
    }//GEN-LAST:event_botaoUltimoActionPerformed

/**/
    private void textoQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoQuantidadeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textoQuantidadeKeyReleased

    private void textoQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoQuantidadeFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_textoQuantidadeFocusLost

    private void textoQuantidadeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_textoQuantidadeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_textoQuantidadeInputMethodTextChanged

    private void textoQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoQuantidadeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_textoQuantidadeKeyTyped

    private void textoValorMaoObraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoValorMaoObraFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_textoValorMaoObraFocusGained

    private void textoValorMaoObraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoValorMaoObraFocusLost
        // TODO add your handling code here:
       
       if(validaCampos()){
        double quantidade = Double.parseDouble(textoQuantidade.getText());
        double valor1 = Double.parseDouble(textoValorMaoObra.getText());
        double valor2 = Double.parseDouble(textoValorSemen.getText());
        double total = (quantidade*valor1)+(quantidade*valor2);
        String totalTexto = String.valueOf(total);
        textoValorTotal.setText(totalTexto);
        
        }
        
    }//GEN-LAST:event_textoValorMaoObraFocusLost

    private void textoValorMaoObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoValorMaoObraActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_textoValorMaoObraActionPerformed

    private void textoValorSemenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoValorSemenFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_textoValorSemenFocusGained

    private void textoValorSemenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoValorSemenFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_textoValorSemenFocusLost

    private void textoValorSemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoValorSemenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoValorSemenActionPerformed

    private void textoValorTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoValorTotalFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_textoValorTotalFocusLost

    private void textoValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoValorTotalActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_textoValorTotalActionPerformed

    private void cbxRacaSemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRacaSemenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxRacaSemenActionPerformed

    private void textoDataInseminacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDataInseminacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoDataInseminacaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormInseminacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormInseminacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormInseminacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormInseminacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormInseminacao dialog = new FormInseminacao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCadastro;
    private javax.swing.JPanel abaLista;
    private javax.swing.JButton botaoAnterior;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPrimeiro;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoUltimo;
    private javax.swing.JComboBox<String> cbxCliente;
    private javax.swing.JComboBox<String> cbxRacaSemen;
    private javax.swing.JComboBox<String> cbxTipoInseminacao;
    private modelo.ConverteData converteData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Cliente> listCliente;
    private java.util.List<Inseminacao> listInseminacao;
    private javax.swing.JTabbedPane painelAbas;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelNavegacao;
    private javax.swing.JTable tabelaInseminacao;
    private javax.swing.JTextField textoCodigo;
    private javax.swing.JFormattedTextField textoDataInseminacao;
    private javax.swing.JFormattedTextField textoHora;
    private javax.swing.JTextField textoQuantidade;
    private javax.swing.JTextField textoValorMaoObra;
    private javax.swing.JTextField textoValorSemen;
    private javax.swing.JTextField textoValorTotal;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
