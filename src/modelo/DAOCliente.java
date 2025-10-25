/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author warme
 */
public class DAOCliente {
    
    DAOLocal daoLocal = new DAOLocal();
    
       public List<Cliente> getLista(){
        String sql = "select * from cliente";
        List<Cliente> listaCliente = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Cliente objCliente = new Cliente();
                objCliente.setCodigo(rs.getInt("codigo"));
                objCliente.setNome(rs.getString("nome"));
                objCliente.setCpf(rs.getString("cpf"));
                
                java.sql.Date dt = rs.getDate("nascimento");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                
                objCliente.setNascimento(c);
                objCliente.setTelefone(rs.getString("telefone"));
                objCliente.setLocal(daoLocal.localizar(rs.getInt("cidade")));
                listaCliente.add(objCliente);
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
        }
        
        return listaCliente;
    }
    
    public boolean salvar(Cliente obj){
        if(obj.getCodigo()==null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
    }
    
    public boolean incluir(Cliente obj){
        String sql = "insert into cliente (nome, cpf, nascimento, telefone, cidade) values(?, ?, ?, ?, ?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCpf());
            pst.setDate(3, new java.sql.Date(obj.getNascimento().getTimeInMillis()));
            pst.setString(4, obj.getTelefone());
            pst.setInt(5, obj.getLocal().getCodigo());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha no cadastro do cliente.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Cliente obj){
        String sql = "update cliente set nome = ?, cpf = ?, nascimento = ?, telefone = ?, cidade = ? where codigo = ?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCpf());
            pst.setDate(3, new java.sql.Date(obj.getNascimento().getTimeInMillis()));
            pst.setString(4, obj.getTelefone());
            pst.setInt(5, obj.getLocal().getCodigo());
            pst.setInt(6, obj.getCodigo());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha na alteração do cliente.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public boolean remover(Cliente obj){
        String sql = "delete from cliente where codigo = ?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigo());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha na exclusão do cliente.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public Cliente localiza2(Integer id){
        String sql = "select * from cliente where codigo = ?";
        Cliente objCliente = new Cliente();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                objCliente.setCodigo(rs.getInt("codigo"));
                objCliente.setNome(rs.getString("nome"));
                objCliente.setCpf(rs.getString("cpf"));
                
                java.sql.Date dt = rs.getDate("nascimento");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                
                objCliente.setNascimento(c);
                objCliente.setTelefone(rs.getString("telefone"));
                objCliente.setLocal(daoLocal.localizar(rs.getInt("cidade")));
                return objCliente;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
        }
        return null;
    }

   
    
    
}
