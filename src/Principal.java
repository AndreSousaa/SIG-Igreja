import CamadaDAO.MembrosDAO;
import CamadaVO.MembrosVO;
import java.io.*;// Comunicação com dispositivos (disco)
import javax.swing.*; //Pacote de elementos graficos (Jrame)
import java.awt.*; //Dimension
import java.awt.event.*;//ActionListener
import java.text.DecimalFormat;
import javax.swing.table.*;//DefaultTableModel
import java.util.*;

//Classe Tela herda caracteristicas da classe JFrame
public class Principal extends JFrame implements ActionListener {

    //declaração publica dos objetos   
    public static Font fntLabels = new Font("Tahoma", Font.BOLD, 24);
    public static Font fntCxTexto = new Font("Verdana", Font.PLAIN, 22);

    //Declaração MENU
    public static JMenuBar mbrTela;
    public static JMenu mnuCadastros, mnuAgendamento, mnuTabelac, mnuControl, mnuFinanceiro, mnuRelatorios, mnuFerramentas, mnuAjuda, mnuEmail;

    public static JMenuItem mniCadastro, mniIgrejas, mniFornecedor, mniCartas;
    public static JMenuItem mniControl, mniMontar, mniRelatorio, mniLimpeza, mniEventos, mniConfig, mniMensagem;
    public static JMenuItem mniContri, mniGrupo, mniRel, mniPlano;
    public static JMenuItem mniContribuicao, mniReabrir, mniCMembros, mniResumo, mniDetalhe, mniPesquisar, mniPesq;
    public static JMenuItem mniCaixa, mniMovi, mniReceber, mniPagar, mniPln, mniCusto, mniTab, mniGerenciar, mniRelatoriof;
    public static JMenuItem mniAniversario, mniRelame, mniCadigreja, mniFuncionario, mniContrime,
            mniDetaest, mniPagarc, mniReceberc, mniFiltro, mniCorrente;
    public static JMenuItem mniItu;
    
    public static JMenuBar mbrPrincipal;
    public static JMenu mnuSistema;
    public static JMenuItem mniLogout;    
    public static Container ctnPrincipal, ctnTopo, ctnMenu;
    public static JLabel lblBanner;    
    public static JButton btnMembro, btnCarteira, btnFun, btnForn, btnCarta, btnAgenda, btnRela,
                            btnTabela, btnContri, btnConsul, btnCaixa, btnReceber, btnPagar, btnSair, btnMenu[];
            
            
    public static JDesktopPane dskJanelas;
    
    public static ImageIcon imgBanner, imgMembro,imgForn, imgCarteira, imgFunc, imgAgenda, imgRela, imgCaixa, imgReceber,
                                imgPagar, imgSair;
   
    //janelas
 
    public static CadastroView objCadastro;
   
    public Principal() { //método construtor

        super("Sistema Gerenciador de Igrejas - SIGI");

        
       
        
        
        //Construção e Configuração do menu
        mbrTela = new JMenuBar();
        this.setJMenuBar(mbrTela); //add barra de menu no JFRame

        //Menu Cadastros
        mnuCadastros = new JMenu("Cadastros");
       // mnuCadastros.setMnemonic('s');
        mbrTela.add(mnuCadastros); // add menu na barra

        mniCadastro = new JMenuItem("Cadastro de Membros e Visistantes");
        mniCadastro.addActionListener(this);
        mnuCadastros.add(mniCadastro);

        mniIgrejas = new JMenuItem("Cadastro de Igrejas");
        mnuCadastros.add(mniIgrejas);

        mniFornecedor = new JMenuItem("Cadastro de Fornecedores e Funcionários");
        mnuCadastros.add(mniFornecedor);

        mniCartas = new JMenuItem("Cadastro de Cartas");
        mnuCadastros.add(mniCartas);

        mniCadastro.add(new JSeparator());
        mniIgrejas.add(new JSeparator());
        mniFornecedor.add(new JSeparator());
        mniCartas.add(new JSeparator());

        //Menu Agendamentos
        mnuAgendamento = new JMenu("Agendamentos");
       // mnuAgendamento.setMnemonic('s');
        mbrTela.add(mnuAgendamento);

        mniControl = new JMenu("1-Controlar Agendamento");
        mnuAgendamento.add(mniControl);

        mniMontar = new JMenu("2-Montar Hórarios da Agenda");
        mnuAgendamento.add(mniMontar);

        mniRelatorio = new JMenu("3-Relatório e Filtro dos Agendamentos");
        mnuAgendamento.add(mniRelatorio);

        mniLimpeza = new JMenu("4-Limpeza dos Agendamentos Abertos e Finalizados");
        mnuAgendamento.add(mniLimpeza);

        mniEventos = new JMenu("5-Cadastro da Tabela de Eventos");
        mnuAgendamento.add(mniEventos);

        mniConfig = new JMenu("6-Configuração Padrão do Agendamento");
        mnuAgendamento.add(mniConfig);

        mniMensagem = new JMenu("7-Mensagem Padrão para Impressão do Agendamento");
        mnuAgendamento.add(mniMensagem);

        mniControl.add(new JSeparator()); //adicionando um separador entre opcçoes
        mniMontar.add(new JSeparator());
        mniRelatorio.add(new JSeparator());
        mniLimpeza.add(new JSeparator());
        mniEventos.add(new JSeparator());
        mniConfig.add(new JSeparator());
        mniMensagem.add(new JSeparator());

        //Menu Tabela de Contribuições
        mnuTabelac = new JMenu("Tabela de Contribuições");
       // mnuTabelac.setMnemonic('s');
        mbrTela.add(mnuTabelac);

        mniContri = new JMenuItem("1-Tabela das Contribuições");
        mnuTabelac.add(mniContri);

        mniGrupo = new JMenuItem("2-Cadastro do Grupo");
        mnuTabelac.add(mniGrupo);

        mniRel = new JMenuItem("3-Relação Tabela das Contribuições");
        mnuTabelac.add(mniRel);

        mniPlano = new JMenuItem("4-Plano de Contas");
        mnuTabelac.add(mniPlano);

        mniContri.add(new JSeparator());
        mniGrupo.add(new JSeparator());
        mniRel.add(new JSeparator());
        mniPlano.add(new JSeparator());

        //Menu Controlar Contribuições
        mnuControl = new JMenu("Controlar Contribuições");
       // mnuControl.setMnemonic('s');
        mbrTela.add(mnuControl);

        mniContribuicao = new JMenuItem("1-Menu das Contribuições");
        mnuControl.add(mniContribuicao);

        mniReabrir = new JMenuItem("2-Reabrir Contribuições Finalizadas");
        mnuControl.add(mniReabrir);

        mniCMembros = new JMenuItem("3-Relatório das Contribuições por Membros");
        mnuControl.add(mniCMembros);

        mniResumo = new JMenuItem("4-Resumo Estatístico das Contribuições");
        mnuControl.add(mniResumo);

        mniDetalhe = new JMenuItem("5-Detalhamento Estatísticos das Contribuições");
        mnuControl.add(mniDetalhe);

        mniPesquisar = new JMenuItem("6-Pesquisar e Gerenciar Recibos");
        mnuControl.add(mniPesquisar);

        mniPesq = new JMenuItem("7-PESQUISAR CONTRIBUIÇÕES");
        mnuControl.add(mniPesq);

        mniContribuicao.add(new JSeparator());
        mniReabrir.add(new JSeparator());
        mniCMembros.add(new JSeparator());
        mniResumo.add(new JSeparator());
        mniDetalhe.add(new JSeparator());
        mniPesquisar.add(new JSeparator());
        mniPesq.add(new JSeparator());

        //Menu Financeiro
        mnuFinanceiro = new JMenu("Financeiro");
        //mnuFinanceiro.setMnemonic('o');
        mbrTela.add(mnuFinanceiro);
        
        mniCaixa = new JMenuItem("1-Cadastro do Caixa");
        mnuFinanceiro.add(mniCaixa);
        
        mniMovi = new JMenuItem("2-Movimento do Caixa");
        mnuFinanceiro.add(mniMovi);
        
        
        mniReceber = new JMenuItem("3-Contas a Receber");
        mnuFinanceiro.add(mniReceber);
        
        mniPagar = new JMenuItem("4-Contas a Pagar");
        mnuFinanceiro.add(mniPagar);
        
        mniPln = new JMenuItem("5-Plano de Contas");
        mnuFinanceiro.add(mniPln);
        
        
        mniCusto = new JMenuItem("6-Cadastro Centro de Custo");
        mnuFinanceiro.add(mniCusto);
        
        mniTab = new JMenuItem("7-Tabela Forma de Pagamento");
        mnuFinanceiro.add(mniTab);
        
        mniGerenciar = new JMenuItem("8-Pesquisar e Gerenciar Recibos");
        mnuFinanceiro.add(mniGerenciar);
        
        mniRelatoriof = new JMenuItem("9-Relatórios Gerenciais do Financeiro");
        mnuFinanceiro.add(mniRelatoriof);
        
        mniCaixa.add(new JSeparator());
        mniMovi.add(new JSeparator());
        mniReceber.add(new JSeparator());
        mniPagar.add(new JSeparator());
        mniPln.add(new JSeparator());
        mniCusto.add(new JSeparator());
        mniTab.add(new JSeparator());
        mniGerenciar.add(new JSeparator());
        mniRelatoriof.add(new JSeparator());
        
        
       

        //Menu Relatórios
        mnuRelatorios = new JMenu("Relatórios");
       // mnuRelatorios.setMnemonic('s');
        mbrTela.add(mnuRelatorios);
        
        
        
            
            mniAniversario = new JMenuItem("1-Relatório dos Aniversariantes"); 
            mnuRelatorios.add(mniAniversario);
            
            
            mniRelame = new JMenuItem("2-Relatório Cadastro de Membros ");     
            mnuRelatorios.add(mniRelame);
            
            mniCadigreja = new JMenuItem("3-Relatório de Cadastro de Igrejas");     
            mnuRelatorios.add(mniCadigreja);
            
            mniFuncionario = new JMenuItem("4-Relatório Fornecedores e Funcionários");     
            mnuRelatorios.add(mniFuncionario);
            
            mniContrime = new JMenuItem("5-Relatório das Contribuições por Membros");     
            mnuRelatorios.add(mniContrime);
            
            mniDetaest = new JMenuItem("6-Resumo Estatístico das Contribuições");     
            mnuRelatorios.add(mniDetaest);
            
            mniPagarc = new JMenuItem("7-Detalhamento Estatístico das Contribuições");     
            mnuRelatorios.add(mniPagarc);
            
            mniReceberc = new JMenuItem("8-Relatório Contas a Pagar");     
            mnuRelatorios.add(mniReceberc);
            
            mniFiltro = new JMenuItem("9-Relatório Contas a Receber");     
            mnuRelatorios.add(mniFiltro);
            
            mniCorrente = new JMenuItem("10-Relatório Caixa / Conta Corrente");     
            mnuRelatorios.add(mniCorrente);
            
                     mniAniversario.add(new JSeparator());
                     mniRelame.add(new JSeparator());
                     mniCadigreja.add(new JSeparator());
                     mniFuncionario.add(new JSeparator());
                     mniContrime.add(new JSeparator());
                     mniDetaest.add(new JSeparator());
                     mniPagarc.add(new JSeparator());
                     mniReceberc.add(new JSeparator());
                     mniFiltro.add(new JSeparator());
                     mniCorrente.add(new JSeparator());
                    
            
            
        //Menu Ferramentas
        /*
        mnuFerramentas = new JMenu("Ferramentas");
        mnuFerramentas.setMnemonic('s');
        mbrTela.add(mnuFerramentas);
            */
        
        
        //Menu Ajuda
        /*
        mnuAjuda = new JMenu("Ajuda");
        mnuAjuda.setMnemonic('a');
        mbrTela.add(mnuAjuda);
        */

        //Menu Email
        mnuEmail = new JMenu("E-mail");
        // mnuEmail.setMnemonic('l');
        mbrTela.add(mnuEmail);

        mnuCadastros.add(new JSeparator());//criando divisao entre itens

        
        
        //Construção e configuração
        ctnPrincipal = new Container();//instanciando
        ctnPrincipal.setLayout(new BorderLayout());//configurando layout
        this.add(ctnPrincipal);//add container na janela 
        
        
        
        imgMembro = new ImageIcon("img/1.png");
        btnMembro = new JButton(imgMembro);
        btnMembro.addActionListener(this);
        btnMembro.setBounds(441,0,45,45);
        ctnPrincipal.add(btnMembro);
        
        imgCarteira = new ImageIcon("img/2.png");
        btnCarteira = new JButton(imgCarteira);
        btnCarteira.setBounds(486,0,45,45);
        ctnPrincipal.add(btnCarteira);
        
        imgFunc = new ImageIcon("img/3.png");
        btnFun = new JButton(imgFunc);
        btnFun.setBounds(531, 0, 45, 45);
        ctnPrincipal.add(btnFun);
       
        imgForn = new ImageIcon("img/4.png");
        btnForn = new JButton(imgForn);
        btnForn.setBounds(576, 0, 45, 45);
        ctnPrincipal.add(btnForn);
        
       imgAgenda = new ImageIcon("img/5.png");
       btnAgenda = new JButton(imgAgenda);
       btnAgenda.setBounds(621,0,45,45);
       ctnPrincipal.add(btnAgenda);
       
       imgRela = new ImageIcon("img/6.png");
       btnRela = new JButton(imgRela);
       btnRela.setBounds(666,0,45,45);
       ctnPrincipal.add(btnRela);
       
       imgCaixa = new ImageIcon("img/7.png");
       btnCaixa = new JButton(imgCaixa);
       btnCaixa.setBounds(711,0,45,45);
       ctnPrincipal.add(btnCaixa);
       
      
       imgPagar = new ImageIcon("img/8.png");
       btnPagar = new JButton(imgPagar);
       btnPagar.setBounds(756,0,45,45);
       ctnPrincipal.add(btnPagar);
       
        imgReceber = new ImageIcon("img/9.png");
       btnReceber = new JButton(imgReceber);
       btnReceber.setBounds(801,0,45,45);
       ctnPrincipal.add(btnReceber);
       
       imgSair = new ImageIcon("img/10.png");
       btnSair = new JButton(imgSair);
       btnSair.addActionListener(this);
       btnSair.setBounds(846, 0, 45, 45);
       ctnPrincipal.add(btnSair);
        
        imgBanner = new ImageIcon("img/background.jpg");
        lblBanner = new JLabel(imgBanner);
        lblBanner.setBounds(0, 0, 1360, 1000);//posicionamento do objeto(X,Y,Larg,Alt)
        ctnPrincipal.add(lblBanner); //add banner no container
         
       
        //encerra processo da VJM ao fechar a janela
      
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(montarTela().width, montarTela().height - 35);//dimensoes da janela
        this.setResizable(false);//bloqueando o dimensionamento
        this.setVisible(true); //exibe a janela

    }//fechando construtor

    public void actionPerformed(ActionEvent event) {

         if(event.getSource() == btnMembro){
            btnMembro.setEnabled(true);
            
           
          
            CadastroView ojj = new CadastroView();
            
            
            carregarTabela(0,"");
            
         }
        if(event.getSource() == btnSair){
        
        int resp = JOptionPane.showConfirmDialog(null, "Você Realmente deseja encerrar o sistema?","Fechar Sistema",JOptionPane.YES_NO_OPTION);
                
                if(resp == JOptionPane.NO_OPTION){
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }else{
                    System.exit(resp);
                }
    }
        
    }//fechando actionPerformed
    
     public static void carregarTabela(int tmpTipo, String tmpBusca) { //carregando tabelas em CadastroView com todos os Membros

        try {

            java.util.List<MembrosVO> lstClientes = new ArrayList<MembrosVO>();  //criando uma Lista puxando os dados da MembroVO

            //limpando lista
            while (CadastroView.dtmMembros.getRowCount() > 0) {
               CadastroView.dtmMembros.removeRow(0);
            }

            //DAO >> VIEW
            lstClientes = MembrosDAO.listarMembros(tmpTipo, tmpBusca);

            for (MembrosVO tmpMembro : lstClientes) {//para cada obj cliente dentro da lista

                String dados[] = new String[7];
                dados[0] = tmpMembro.getRg();
                dados[1] = tmpMembro.getNome();
                dados[2] = tmpMembro.getDataNascimento();
                dados[3] = tmpMembro.getCargo();
                dados[4] = tmpMembro.getEndereco();
                dados[5] = tmpMembro.getTelefone();
                dados[6] = tmpMembro.getFoto();

               CadastroView.dtmMembros.addRow(dados);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        
     } //fechando CarregarTabela

    public static Dimension montarTela() {
        //Classe ToolKit - acessa configurações do sistema
        Toolkit info = Toolkit.getDefaultToolkit();
        Dimension resolucao = info.getScreenSize();//acessando dimensoes da tela
        return resolucao;//retornando resolução
    } //fechando montarTela

}//fechando classe
