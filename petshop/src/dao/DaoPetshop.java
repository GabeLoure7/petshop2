/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.PetshopModelo;
public class DaoPetshop {
     public static boolean inserir(PetshopModelo objeto) {
        String sql = "INSERT INTO petshop (nome, endereco, numero, avaliacao) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEndereco());
            ps.setInt(3, objeto.getNumero());
            ps.setInt(4, objeto.getAvaliacao());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static void main(String[] args) {
        PetshopModelo objeto = new PetshopModelo();
        objeto.setNome("?");
        objeto.setEndereco("?");
        objeto.setNumero(2);
        objeto.setAvaliacao(10);
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
      public static boolean alterar(PetshopModelo objeto) {
        String sql = "UPDATE petshop SET nome = ?, avaliacao = ?, numero = ?, endereco = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome()); 
             ps.setInt(2, objeto.getAvaliacao());
            ps.setInt(3, objeto.getNumero());
            ps.setString(4, objeto.getEndereco()); 
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean excluir(PetshopModelo objeto) {
        String sql = "DELETE FROM petshop WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static List<PetshopModelo> consultar() {
        List<PetshopModelo> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, numero, avaliacao, endereco FROM petshop";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PetshopModelo objeto = new PetshopModelo();
                
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setNumero(rs.getInt("numero"));
                objeto.setAvaliacao(rs.getInt("avaliacao"));
                objeto.setEndereco(rs.getString("endereco"));
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
}
