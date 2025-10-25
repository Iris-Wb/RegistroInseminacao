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
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author warme
 */
public class DAOLocal {
    
    public List<Local> getLista(){
        
        String sql = "select * from local";
        List<Local> listaLocal = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Local objLocal = new Local();
                objLocal.setCodigo(rs.getInt("codigo"));
                objLocal.setRua(rs.getString("ruaFazenda"));
                objLocal.setBairro(rs.getString("bairro"));
                objLocal.setCidade(rs.getString("cidade"));
                objLocal.setCep(rs.getString("cep"));
                objLocal.setUf(rs.getString("uf"));
                listaLocal.add(objLocal);
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
        }
        
        return listaLocal;
    }
    
    public boolean salvar(Local obj){
        if(obj.getCodigo()==null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
    }
    
    public boolean incluir(Local obj){
        String sql = "insert into local (ruaFazenda, bairro, cidade, cep, uf) values(?, ?, ?, ?, ?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getRua());
            pst.setString(2, obj.getBairro());
            pst.setString(3, obj.getCidade());
            pst.setString(4, obj.getCep());
            pst.setString(5, obj.getUf());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Local cadastrado com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha no cadastro do local.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Local obj){
        String sql = "update local set ruaFazenda = ?, bairro = ?, cidade = ?, cep = ?, uf = ? where codigo = ?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getRua());
            pst.setString(2, obj.getBairro());
            pst.setString(3, obj.getCidade());
            pst.setString(4, obj.getCep());
            pst.setString(5, obj.getUf());
            pst.setInt(6, obj.getCodigo());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Local alterado com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha na alteração do local.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public boolean remover(Local obj){
        String sql = "delete from local where codigo = ?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigo());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Local excluído com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha na exclusão do local.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public Local localizar(Integer id){
        String sql = "select * from local where codigo = ?";
        Local objLocal = new Local();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                objLocal.setCodigo(rs.getInt("codigo"));
                objLocal.setRua(rs.getString("ruaFazenda"));
                objLocal.setBairro(rs.getString("bairro"));
                objLocal.setCidade(rs.getString("cidade"));
                objLocal.setCep(rs.getString("cep"));
                objLocal.setUf(rs.getString("uf"));
                return objLocal;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
        }
        return null;
    }
    
}
