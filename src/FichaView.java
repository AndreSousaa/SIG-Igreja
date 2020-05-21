
import CamadaDAO.MembrosDAO;
import CamadaVO.MembrosVO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.channels.*;
import java.util.ArrayList;
import javax.swing.filechooser.*;
import javax.swing.text.MaskFormatter;

public class FichaView extends JFrame implements ActionListener {

    public static JLabel lbNome, lbCidade, lbBairro, lbEndereco, lbCelular, lbSexo, lbProfissao,
            lbNasc, lbPais, lbFoto, lbNaIgreja, lbDizimista, lbOfertante, lbBatizado, lbCargo, lbRg;

    public static JLabel lblSalvar, lblExclui, lblEditar;

    public static JComboBox cbDizimista, cbOfertante, cbBatizado, cbSexo;

    public static JTextField tfNome, tfCidade, tfBairro, tfEndereco, tfCelular, tfSexo, tfProfissao,
            tfNasc, tfPais, tfFoto, tfBatizado, tfNaIgreja, tfDizimista, tfCargo, tfOfertante, tfRg;

    public static ImageIcon imgFoto, imgEditar, imgExcluir, imgSalvar;

    public static JButton btnSalvar, btnFoto, btnEditar, btnExcluir;

    public static JFormattedTextField fmCelular;

    //Declaração de variaveis e objetos auxiliares
    public static FileChannel flcOrigem, flcDestino;//cópia
    public static FileInputStream flsEntrada;//leitura
    public static FileOutputStream flsSaida;//leitura
    public static String strCaminhoOrigem, strCaminhoDestino, strNomeArquivoOrigem, extensao;
    public static int statusFoto;
    public static int statusAtual = 0, acao;
    public static boolean status;

    public static Container ctnFicha;

    public FichaView() {
        super("Ficha de Cadastro Membros - IBRJPD");

        ctnFicha = new Container(); //container Ficha
        ctnFicha.setLayout(null);
        this.add(ctnFicha);

        lbNome = new JLabel("Nome:");
        lbNome.setBounds(10, 20, 45, 20);
        ctnFicha.add(lbNome);
        //TextField
        tfNome = new JTextField();
        tfNome.setBounds(10, 45, 300, 20);
        tfNome.getText();
        ctnFicha.add(tfNome);

        lbRg = new JLabel("R.G:");
        lbRg.setBounds(10, 70, 30, 20);
        ctnFicha.add(lbRg);
        //textField
        tfRg = new JTextField();
        tfRg = new JFormattedTextField(Mascara("##.###.###-#"));
        tfRg.setBounds(10, 95, 300, 20);
        tfRg.getText();
        ctnFicha.add(tfRg);

        lbCelular = new JLabel("Celular:");
        lbCelular.setBounds(110, 120, 80, 20);
        ctnFicha.add(lbCelular);
        //TextField
        tfCelular = new JTextField();
        tfCelular = new JFormattedTextField(Mascara("(##) #####-####"));
        tfCelular.setBounds(110, 145, 100, 20);
        tfCelular.getText();
        ctnFicha.add(tfCelular);

        lbNasc = new JLabel("Nascimento:");
        lbNasc.setBounds(10, 120, 80, 20);
        ctnFicha.add(lbNasc);
        //TextField
        tfNasc = new JTextField();
        tfNasc = new JFormattedTextField(Mascara("##/##/####"));

        tfNasc.setBounds(10, 145, 80, 20);
        tfNasc.getText();
        ctnFicha.add(tfNasc);

        lbEndereco = new JLabel("Endereço:");
        lbEndereco.setBounds(10, 170, 80, 20); //Horizontal, Vertical, Largura e Altura
        ctnFicha.add(lbEndereco);
        //TextField
        tfEndereco = new JTextField();
        tfEndereco.setBounds(10, 195, 300, 20);
        tfEndereco.getText();
        ctnFicha.add(tfEndereco);

        lbBairro = new JLabel("Bairro:");
        lbBairro.setBounds(10, 220, 50, 20);
        ctnFicha.add(lbBairro);
        //TextField
        tfBairro = new JTextField();
        tfBairro.setBounds(10, 245, 150, 20);
        tfBairro.getText();
        ctnFicha.add(tfBairro);

        lbCidade = new JLabel("Cidade:");
        lbCidade.setBounds(170, 220, 50, 20);
        ctnFicha.add(lbCidade);
        //TextField
        tfCidade = new JTextField();
        tfCidade.setBounds(170, 245, 142, 20);
        tfCidade.getText();
        ctnFicha.add(tfCidade);

        lbSexo = new JLabel("Sexo:");
        lbSexo.setBounds(230, 120, 40, 20);
        ctnFicha.add(lbSexo);

        lbProfissao = new JLabel("Profissão:");
        lbProfissao.setBounds(10, 270, 63, 20);
        ctnFicha.add(lbProfissao);
        //TextField
        tfProfissao = new JTextField();
        tfProfissao.setBounds(10, 295, 150, 20);
        tfProfissao.getText();
        ctnFicha.add(tfProfissao);

        lbPais = new JLabel("Nacionalidade:");
        lbPais.setBounds(170, 270, 130, 20);
        ctnFicha.add(lbPais);
        //TextField
        tfPais = new JTextField();
        tfPais.setBounds(170, 295, 142, 20);
        tfPais.getText();
        ctnFicha.add(tfPais);

        lbCargo = new JLabel("Cargo:");
        lbCargo.setBounds(10, 320, 50, 20);
        ctnFicha.add(lbCargo);
        //TextField
        tfCargo = new JTextField();
        tfCargo.setBounds(10, 345, 300, 20);
        tfCargo.getText();
        ctnFicha.add(tfCargo);

        lbBatizado = new JLabel("Batizado:");
        lbBatizado.setBounds(10, 380, 80, 20);
        ctnFicha.add(lbBatizado);

        lbOfertante = new JLabel("Ofertante:");
        lbOfertante.setBounds(110, 380, 80, 20);
        ctnFicha.add(lbOfertante);

        lbDizimista = new JLabel("Dizimista:");
        lbDizimista.setBounds(218, 380, 80, 20);
        ctnFicha.add(lbDizimista);

        //Combo Box 
        String op[] = {"Selecione", "Sim", "Não"};
        cbDizimista = new JComboBox(op);
        cbDizimista.setBounds(218, 405, 90, 20);
        cbDizimista.addActionListener(this);
        ctnFicha.add(cbDizimista);

        String ab[] = {"Selecione", "Sim", "Não"};
        cbOfertante = new JComboBox(ab);
        cbOfertante.setBounds(110, 405, 90, 20);
        cbOfertante.addActionListener(this);
        ctnFicha.add(cbOfertante);

        String ac[] = {"Selecione", "Sim", "Não"};
        cbBatizado = new JComboBox(ac);
        cbBatizado.setBounds(10, 405, 90, 20);
        cbBatizado.addActionListener(this);
        ctnFicha.add(cbBatizado);

        String ad[] = {"Selecione", "Masculino", "Feminino"};
        cbSexo = new JComboBox(ad);
        cbSexo.setBounds(230, 145, 80, 20);
        cbSexo.getSelectedItem();
        cbSexo.addActionListener(this);
        ctnFicha.add(cbSexo);

        //FOTO de Usuario
        imgFoto = new ImageIcon("img/user.png");
        lbFoto = new JLabel(imgFoto);
        lbFoto.setBounds(370, 20, 128, 128);
        ctnFicha.add(lbFoto);

        //JButton Escolher Foto
        btnFoto = new JButton("Selecionar imagem");
        btnFoto.addActionListener(this);
        btnFoto.setBounds(360, 160, 150, 20); //horizontal , vertical, largura, altura
        ctnFicha.add(btnFoto);

        //Botoes
        imgSalvar = new ImageIcon("img/salvar.png");
        btnSalvar = new JButton(imgSalvar);
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(360, 200, 40, 40);
        ctnFicha.add(btnSalvar);

        imgEditar = new ImageIcon("img/editar.png");
        btnEditar = new JButton(imgEditar);
        btnEditar.setBounds(418, 200, 40, 40);
        ctnFicha.add(btnEditar);

        imgExcluir = new ImageIcon("img/excluir.png");
        btnExcluir = new JButton(imgExcluir);
        btnExcluir.setBounds(475, 200, 35, 40);
        ctnFicha.add(btnExcluir);

        //---------------------------//////
        this.setVisible(true);
        this.setSize(550, 550);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public MaskFormatter Mascara(String Mascara) {

        MaskFormatter F_Mascara = new MaskFormatter();
        try {
            F_Mascara.setMask(Mascara); //Atribui a mascara
            F_Mascara.setPlaceholderCharacter(' '); //Caracter para preencimento 
        } catch (Exception excecao) {
            excecao.printStackTrace();
        }
        return F_Mascara;
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnSalvar) {

            //salvar a foto
                    int ultimoPonto = strNomeArquivoOrigem.lastIndexOf(".");//pegando a posição do ultimo ponto
                    extensao = strNomeArquivoOrigem.substring(ultimoPonto + 1, strNomeArquivoOrigem.length());
                    strCaminhoDestino = "img\\membros\\" + tfRg.getText() + "." + extensao;

                    try {
                        flsEntrada = new FileInputStream(strCaminhoOrigem);
                        flsSaida = new FileOutputStream(strCaminhoDestino);

                        flcOrigem = flsEntrada.getChannel();
                        flcDestino = flsSaida.getChannel();

                        //cópia total do arquivo
                        flcOrigem.transferTo(0, flcOrigem.size(), flcDestino);

                        flcOrigem.close();
                        flcDestino.close();

                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage());
                    }
         
            try {
                MembrosVO novoMembro = new MembrosVO();
                //preenchendo objeto
                novoMembro.setNome(tfNome.getText());
                novoMembro.setRg(tfRg.getText());
                novoMembro.setDataNascimento(tfNasc.getText());
                novoMembro.setTelefone(tfCelular.getText());
                novoMembro.setSexo((String) cbSexo.getSelectedItem());
                novoMembro.setEndereco(tfEndereco.getText());
                novoMembro.setBairro(tfBairro.getText());
                novoMembro.setCidade(tfCidade.getText());
                novoMembro.setProfissao(tfProfissao.getText());
                novoMembro.setNacionalidade(tfPais.getText());
                novoMembro.setCargo(tfCargo.getText());
                novoMembro.setBatizado((String) cbBatizado.getSelectedItem());
                novoMembro.setOfertante((String) cbOfertante.getSelectedItem());
                novoMembro.setDizimista((String) cbDizimista.getSelectedItem());
                novoMembro.setFoto((String)tfRg.getText());

                MembrosDAO.cadastrarMembro(novoMembro);
                JOptionPane.showMessageDialog(null, "Membro " + novoMembro.getNome() + "\n RG " + novoMembro.getRg() + "  cadastrado.");
                carregarDados(0, "");
                     
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(null, evt.getMessage());
            } //fechando Catch

        } else if (event.getSource() == btnFoto) {

            JFileChooser flcFoto = new JFileChooser("C:\\Users\\andre\\Documents");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de imagem(*.png, *.jpg)", "png", "jpg");
            flcFoto.setFileFilter(filtro);//vinculando chooser ao filtro
            statusFoto = flcFoto.showOpenDialog(this);//abre o explorer

            //preview da imagem
            strCaminhoOrigem = flcFoto.getSelectedFile().getPath();
            strNomeArquivoOrigem = flcFoto.getSelectedFile().getName();
            lbFoto.setIcon(new ImageIcon(strCaminhoOrigem));

        }

    }//fechando ActionPerdormed

    public static void carregarDados(int tmpTipo, String tmpBusca) {

        try {

            java.util.List<MembrosVO> lstClientes = new ArrayList<MembrosVO>();

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

    }//fechando carregarDados

   
}
