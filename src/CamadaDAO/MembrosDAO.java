package CamadaDAO;


import CamadaDAO.ConexaoDAO;
import CamadaVO.MembrosVO;
import java.sql.*;
import java.util.*; //List

public class MembrosDAO {

    public static Statement stMembros; //executa SQL
    public static ResultSet rsMembros; //armazena result do select

    public static void cadastrarMembro(MembrosVO tmpMembro) throws Exception {

        try {
            ConexaoDAO.abrirConexao(); //Classe para abrir Conexao.Metodo
            //montagem do insert
            
            String sqlMembro = "";
            sqlMembro += "Insert into membros(";
            sqlMembro += "nome_MEMBRO, rg_MEMBRO,";
            sqlMembro += "nascimento_MEMBRO, telefone_MEMBRO,";
            sqlMembro += "sexo_MEMBRO, endereco_MEMBRO,";
            sqlMembro += "bairro_MEMBRO, cidade_MEMBRO,";
            sqlMembro += "profissao_MEMBRO, pais_MEMBRO,";
            sqlMembro += "cargo_MEMBRO, batizado_MEMBRO,";
            sqlMembro += "ofertante_MEMBRO, dizimista_MEMBRO,";
            sqlMembro += "foto_MEMBRO)";
            sqlMembro += "values(";
            sqlMembro += "'" + tmpMembro.getNome() + "',";
            sqlMembro += "'" + tmpMembro.getRg() + "',";
            sqlMembro += "'" + tmpMembro.getDataNascimento()+ "',";
            sqlMembro += "'" + tmpMembro.getTelefone()+ "',";
            sqlMembro += "'" + tmpMembro.getSexo()+ "',";
            sqlMembro += "'" + tmpMembro.getEndereco()+ "',";
            sqlMembro += "'" + tmpMembro.getBairro()+ "',";
            sqlMembro += "'" + tmpMembro.getCidade()+ "',";
            sqlMembro += "'" + tmpMembro.getProfissao()+ "',";
            sqlMembro += "'" + tmpMembro.getNacionalidade()+ "',";
            sqlMembro += "'" + tmpMembro.getCargo()+ "',";
            sqlMembro += "'" + tmpMembro.getBatizado()+ "',";
            sqlMembro += "'" + tmpMembro.getOfertante()+ "',";
            sqlMembro += "'" + tmpMembro.getDizimista()+ "',";
            sqlMembro += "'" + tmpMembro.getFoto()+ "')";
            
      
             //preparando statement para execução do INSERT
            stMembros = ConexaoDAO.connSistema.createStatement();

            //execução do insert
            stMembros.executeUpdate(sqlMembro);

            ConexaoDAO.fecharConexao();

        } catch (SQLException erro) {
            String msg = "Falha no procedimento de cadastro de cliente.\n"
                    + "Verifique a sintaxe da instrução Insert e nomes de campos e tabelas.\n\n"
                    + "Erro Original: " + erro.getMessage();

            throw new Exception(msg);
    
            

}


    }// fechando cadastrar Membros
    
     public static MembrosVO consultarMembro(String tmpRg) throws Exception {

        try {
            ConexaoDAO.abrirConexao();

            MembrosVO tmpMembro = new MembrosVO();

            String sqlConsulta = "Select * from membros where rg_MEMBRO like '" + tmpRg + "'";

            //preparando statement
            stMembros = ConexaoDAO.connSistema.createStatement();
            rsMembros = stMembros.executeQuery(sqlConsulta);

            if (rsMembros.next()) {//se houver registros

             
                tmpMembro.setRg(rsMembros.getString("rg_MEMBRO"));
                tmpMembro.setNome(rsMembros.getString("nome_MEMBRO"));
                tmpMembro.setDataNascimento(rsMembros.getString("nascimento_MEMBRO"));
                tmpMembro.setTelefone(rsMembros.getString("telefone_MEMBRO"));
                tmpMembro.setSexo(rsMembros.getString("sexo_MEMBRO"));
                tmpMembro.setEndereco(rsMembros.getString("endereco_MEMBRO"));
                tmpMembro.setBairro(rsMembros.getString("bairro_MEMBRO"));
                tmpMembro.setCidade(rsMembros.getString("cidade_MEMBRO"));
                tmpMembro.setProfissao(rsMembros.getString("profissao_MEMBRO"));
                tmpMembro.setNacionalidade(rsMembros.getString("pais_MEMBRO"));
                tmpMembro.setCargo(rsMembros.getString("cargo_MEMBRO"));
                tmpMembro.setBatizado(rsMembros.getString("batizado_MEMBRO"));
                tmpMembro.setOfertante(rsMembros.getString("ofertante_MEMBRO"));
                tmpMembro.setDizimista(rsMembros.getString("dizimista_MEMBRO"));
                tmpMembro.setFoto(rsMembros.getString("foto_MEMBRO"));
                

                ConexaoDAO.fecharConexao();
                return tmpMembro;
            }

            ConexaoDAO.fecharConexao();
            return null; // saida 1 - return            

        } catch (Exception erro) {
            String msg = "Falha na consulta do Membro.\n"
                    + "Verifique a sintaxe da instrução Select e nomes de campos e tabelas.\n\n"
                    + "Erro Original: " + erro.getMessage();

            throw new Exception(msg); //saida 2
        }

    }//fechando consultar
     
      public static List<MembrosVO> listarMembros(int tmpTipo, String tmpBusca) throws Exception {

        try {
            ConexaoDAO.abrirConexao();

            List<MembrosVO> lstMembros = new ArrayList<MembrosVO>();

            String sqlLista = "";

            if (tmpTipo == 0) {
                sqlLista = "Select * from membros";

            } else if (tmpTipo == 1) {
                sqlLista = "Select * from membros where rg_MEMBRO like '%" + tmpBusca + "%'";

            } else if (tmpTipo == 2) {
                sqlLista = "Select * from membros where nome_MEMBRO like '%" + tmpBusca + "%'";
            } else if (tmpTipo == 3) {
                sqlLista = "Select * from membros where nascimento_MEMBRO like '%" + tmpBusca + "%'";
            }else if (tmpTipo == 4)  {
                sqlLista = "Select * from membros where cargo_MEMBRO like '%" + tmpBusca + "%'";
                
            }else if (tmpTipo == 5) {
                sqlLista = "Select * from membros where endereco_MEMBRO '%" + tmpBusca + "%'";
            }else if (tmpTipo == 6){
                sqlLista = "Select * from membros where celular_MEMBRO '%" + tmpBusca + "%'";
            }else if(tmpTipo == 7){
                sqlLista = "Select * from membros where foto_MEMBRO '%" + tmpBusca + "&'";
            }
                

            //preparando statement
            stMembros = ConexaoDAO.connSistema.createStatement();
            rsMembros = stMembros.executeQuery(sqlLista);

            while (rsMembros.next()) {

                MembrosVO tmpMembro = new MembrosVO();//instanciando obj Membro

               
                tmpMembro.setRg(rsMembros.getString("rg_MEMBRO"));
                tmpMembro.setNome(rsMembros.getString("nome_MEMBRO"));
                tmpMembro.setDataNascimento(rsMembros.getString("nascimento_MEMBRO"));
                tmpMembro.setTelefone(rsMembros.getString("telefone_MEMBRO"));
                tmpMembro.setSexo(rsMembros.getString("sexo_MEMBRO"));
                tmpMembro.setEndereco(rsMembros.getString("endereco_MEMBRO"));
                tmpMembro.setBairro(rsMembros.getString("bairro_MEMBRO"));
                tmpMembro.setCidade(rsMembros.getString("cidade_MEMBRO"));
                tmpMembro.setProfissao(rsMembros.getString("profissao_MEMBRO"));
                tmpMembro.setNacionalidade(rsMembros.getString("pais_MEMBRO"));
                tmpMembro.setCargo(rsMembros.getString("cargo_MEMBRO"));
                tmpMembro.setBatizado(rsMembros.getString("batizado_MEMBRO"));
                tmpMembro.setOfertante(rsMembros.getString("ofertante_MEMBRO"));
                tmpMembro.setDizimista(rsMembros.getString("dizimista_MEMBRO"));
                tmpMembro.setFoto(rsMembros.getString("foto_MEMBRO"));

                lstMembros.add(tmpMembro);
            }

            ConexaoDAO.fecharConexao();
            return lstMembros; // saida 1 - return            

        } catch (Exception erro) {
            String msg = "Falha na listagem dos dados.\n"
                    + "Verifique a sintaxe da instrução Select e nomes de campos e tabelas.\n\n"
                    + "Erro Original: " + erro.getMessage();

            throw new Exception(msg); //saida 2
        }

    }//fechando listarClientes - saida 3

    
      public static void excluirMembro (String tmpRg) throws Exception{
          
          try{
              ConexaoDAO.abrirConexao();
              
              String sqlExcluir = "Delete from membros where  rg_MEMBRO like '" + tmpRg + "'";
              stMembros = ConexaoDAO.connSistema.createStatement();
              stMembros.executeUpdate(sqlExcluir);
              
              ConexaoDAO.fecharConexao();
              
          }catch(Exception erro){
              String msg = "Falha na exclusão de dados. "
                    + "Verifique a sintaxe do DELETE e "
                    + "o nome de campos e tabelas.\n\n"
                    + "Erro Original:" + erro.getMessage();
            
            throw new Exception(msg);
          }
      }//fechando excluir Membro
    
}