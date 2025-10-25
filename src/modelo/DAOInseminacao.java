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
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author warme
 */
public class DAOInseminacao {
    
    DAOCliente daoCliente = new DAOCliente();
    
        public List<Inseminacao> getLista(){
        String sql = "select * from inseminacao";
        List<Inseminacao> listaInseminacao = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Inseminacao objInseminacao = new Inseminacao();
                objInseminacao.setCodigo(rs.getInt("codigo"));
                objInseminacao.setCliente(daoCliente.localiza2(rs.getInt("cliente")));
                
                java.sql.Date dt = rs.getDate("data");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                
                objInseminacao.setData(c);
                objInseminacao.setHora(rs.getString("hora"));
                objInseminacao.setRaca(rs.getString("raca"));
                objInseminacao.setTipo(rs.getString("tipo"));
                objInseminacao.setQuantidade(rs.getInt("quantidade"));
                objInseminacao.setValorSemen(rs.getDouble("valorSemen"));
                objInseminacao.setMaoObra(rs.getDouble("maoObra"));
                objInseminacao.setPrecoTotal(rs.getDouble("precoTotal"));
                listaInseminacao.add(objInseminacao);
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
        }
        
        return listaInseminacao;
    }
    
    public boolean salvar(Inseminacao obj){
        if(obj.getCodigo()==null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
    }
    
    public boolean incluir(Inseminacao obj){
        String sql = "insert into inseminacao (cliente, data, hora, raca, tipo, quantidade, valorSemen, maoObra, precoTotal) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCliente().getCodigo());
            pst.setDate(2, new java.sql.Date(obj.getData().getTimeInMillis()));
            pst.setString(3, obj.getHora());
            pst.setString(4, obj.getRaca());
            pst.setString(5, obj.getTipo());
            pst.setInt(6, obj.getQuantidade());
            pst.setDouble(7, obj.getValorSemen());
            pst.setDouble(8, obj.getMaoObra());
            pst.setDouble(9, obj.getPrecoTotal());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Inseminação cadastrada com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha no cadastro da inseminação.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Inseminacao obj){
        String sql = "update inseminacao set cliente = ?, data = ?, hora = ?, raca = ?, tipo = ?, quantidade = ?, valorSemen = ?, maoObra = ?, precoTotal = ? where codigo = ?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCliente().getCodigo());
            pst.setDate(2, new java.sql.Date(obj.getData().getTimeInMillis()));
            pst.setString(3, obj.getHora());
            pst.setString(4, obj.getRaca());
            pst.setString(5, obj.getTipo());
            pst.setInt(6, obj.getQuantidade());
            pst.setDouble(7, obj.getValorSemen());
            pst.setDouble(8, obj.getMaoObra());
            pst.setDouble(9, obj.getPrecoTotal());
            pst.setInt(10, obj.getCodigo());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Inseminação alterada com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha na alteração da inseminação.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    public boolean remover(Inseminacao obj){
        String sql = "delete from inseminacao where codigo = ?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigo());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Inseminação excluída com sucesso.");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Falha na exclusão da inseminação.");
                return false;
            }
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de SQL "+erro.getMessage());
            return false;
        }
    }
    
    
}
