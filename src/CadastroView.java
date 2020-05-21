
import CamadaDAO.MembrosDAO;
import CamadaVO.MembrosVO;
import java.io.*;// Comunicação com dispositivos (disco)
import javax.swing.*; //Pacote de elementos graficos (Jrame)
import java.awt.*; //Dimension
import java.awt.event.*;//ActionListener
import java.nio.channels.FileChannel;
import javax.swing.table.*;//DefaultTableModel
import java.util.*;

public class CadastroView extends JFrame implements ActionListener {

    public static Container ctnCadastro; 

    public static JButton btnNovo, btnEditar, btnExcluir, btnImprimir, btnVoltar; 

    public static ImageIcon imgNovo, imgEditar, imgExcluir, imgVoltar, imgImprimir; 

    public static JTextField txtBuscar; 

    public static JLabel lbFoto, lblBuscar; 
      public static FileChannel flcOrigem, flcDestino;//cópia
    public static FileInputStream flsEntrada;//leitura
    public static FileOutputStream flsSaida;//leitura
    public static String strCaminhoOrigem, strCaminhoDestino, strNomeArquivoOrigem, extensao;

    public static String strTopo[] = {"RG","Nome", "Data de Nascimento", "Cargo", "Endereço", "Telefone","Foto"};
    public static JScrollPane scrMembros; 
    public static JTable tblMembros;
    public static DefaultTableModel dtmMembros;
    public static ImageIcon imgFoto;

    public CadastroView() {
        super("CADASTRO DE MEMBROS E VISITANTES - IBRJPD");

        ctnCadastro = new Container();
        ctnCadastro.setLayout(null); 
        this.add(ctnCadastro); 

        imgNovo = new ImageIcon("img/New.png"); 
        btnNovo = new JButton("Novo",imgNovo); 
        btnNovo.addActionListener(this);
        btnNovo.setBounds(1100, 300, 150, 40); 
        ctnCadastro.add(btnNovo); 

        imgEditar = new ImageIcon("img/edit.png");
        btnEditar = new JButton("Editar",imgEditar); 
        btnEditar.setBounds(1100, 350, 150, 40); 
        ctnCadastro.add(btnEditar); 

        imgExcluir = new ImageIcon("img/delet.png"); 
        btnExcluir = new JButton("Excluir",imgExcluir); 
        btnExcluir.addActionListener(this); 
        btnExcluir.setBounds(1100, 400, 150, 40); 
        ctnCadastro.add(btnExcluir); 

        imgVoltar = new ImageIcon("img/back.png"); 
        btnVoltar = new JButton("Voltar",imgVoltar);
        btnVoltar.addActionListener(this); 
        btnVoltar.setBounds(1100, 567, 150, 40); 
        ctnCadastro.add(btnVoltar); 

        imgImprimir = new ImageIcon("img/printer.png"); 
        btnImprimir = new JButton("Imprimir",imgImprimir);
        btnImprimir.addActionListener(this); 
        btnImprimir.setBounds(1100, 450, 150, 40); 
        ctnCadastro.add(btnImprimir);

        
        txtBuscar = new JTextField();  
        txtBuscar.setBounds(20, 70, 1000, 25);
        ctnCadastro.add(txtBuscar); 

        //lblBuscar = new JLabel("Buscar"); 
//        lblBuscar.setBounds(20, 70, 50, 25); 
//        ctnCadastro.add(lblBuscar); 

        imgFoto = new ImageIcon("img/user.png");
        lbFoto = new JLabel(imgFoto);
        lbFoto.setBorder(BorderFactory.createLineBorder(Color.black));
        lbFoto.setBounds(1095, 100, 160, 160);
        ctnCadastro.add(lbFoto);
        
        //--------Iniciando Tabela--------------
        tblMembros = new JTable(); 
        scrMembros = new JScrollPane(tblMembros); 
        dtmMembros = (DefaultTableModel) tblMembros.getModel(); 

//        Inserindo elementos no topo da tabela
        for (int i = 0; i < strTopo.length; i++) { 
            dtmMembros.addColumn(strTopo[i]); 
        }

        scrMembros.setBounds(20, 105, 1000, 500); 
        ctnCadastro.add(scrMembros); 

         tblMembros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    String tmpRg = tblMembros.getValueAt(tblMembros.getSelectedRow(), 0).toString();
                   
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro);
                }
            }
        });
        //encerra processo da VJM ao fechar a janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Evento que define o que ira acontecer ao fechar a janela, nesse caso encerrará o sistema
        this.setSize(montarTela().width, montarTela().height - 35);//dimensoes da janela
        this.setResizable(false);//bloqueando o dimensionamento
        this.setVisible(true); //metodo que permite exibir a janela

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btnVoltar) {
            btnVoltar.setEnabled(true); 
            this.dispose(); 

        }
        if (ae.getSource() == btnNovo) {
            btnNovo.setEnabled(true); 

            FichaView tp = new FichaView(); 
            tp.setVisible(true); 
            
            
        }else if(ae.getSource() == btnExcluir){
            
            try{
                 String tmpRg = tblMembros.getValueAt(tblMembros.getSelectedRow(), 0).toString();
                 String tmpNome = tblMembros.getValueAt(tblMembros.getSelectedRow(), 1).toString();
                  
                
                
                int verif = JOptionPane.showConfirmDialog(
                            null, "Deseja realmente excluir " + 
                                   tmpNome,
                                   "Exclusão de Dados",
                                    JOptionPane.YES_NO_OPTION);
                
                if(verif == JOptionPane.YES_OPTION){
                   // MouseEvent.
                    MembrosDAO.excluirMembro(tmpRg);
                    JOptionPane.showMessageDialog(null,"Nome: "+ tmpNome + "\n RG: " + tmpRg +"\n   EXCLUIDO!");
                    FichaView.carregarDados(0,"");                    
                }
                    btnExcluir.setEnabled(true);
                    
                
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Você precisa selecionar um \n   Membro ou Visitante!");
            }
            
        }

    }//fechando actionPerformed
    
     
    public static Dimension montarTela() { //Classe de Dimensionamento da Janela
        //Classe ToolKit - acessa configurações do sistema
        Toolkit info = Toolkit.getDefaultToolkit(); //recebe as informações padroes da tela 
        Dimension resolucao = info.getScreenSize();//acessando dimensoes da tela
        return resolucao;//retornando resolução a tela
    } //fechando montarTela
    
       public static void carregarCampos(MembrosVO tmpMembro) {
           
          FichaView.tfRg.setText(tmpMembro.getRg());
          FichaView.tfNome.setText(tmpMembro.getNome());
          FichaView.tfNasc.setText(tmpMembro.getDataNascimento());
          FichaView.tfEndereco.setText(tmpMembro.getEndereco());
          FichaView.tfBairro.setText(tmpMembro.getBairro());
          FichaView.tfCidade.setText(tmpMembro.getCidade());
          FichaView.tfCelular.setText(tmpMembro.getTelefone());
          FichaView.tfCargo.setText(tmpMembro.getCargo());
          FichaView.tfPais.setText(tmpMembro.getNacionalidade());
          FichaView.tfProfissao.setText(tmpMembro.getProfissao());
          FichaView.tfBatizado.setText(tmpMembro.getBatizado());
          FichaView.tfOfertante.setText(tmpMembro.getOfertante());
          FichaView.tfDizimista.setText(tmpMembro.getDizimista());
          FichaView.tfSexo.setText(tmpMembro.getSexo());

//        lbFoto.setIcon(new ImageIcon("img/membros/" + tmpMembro.getFoto()));
//   
            //lbFoto.setIcon(new ImageIcon("img/membros/" + tmpMembro.getRg()));     
    
      }

    
}//fechando Classe

