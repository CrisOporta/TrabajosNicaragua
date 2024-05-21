package data;

import android.content.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Empleo;
import models.Usuario;

public class DBHelper {
    private PostgreSQLHelper postgreSQLHelper;

    public DBHelper() {
        postgreSQLHelper = new PostgreSQLHelper();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            postgreSQLHelper.disconnect();
        } finally {
            super.finalize();
        }
    }

    // ----------------------------------------------------------------------------------------
    // Usuario CRUD----------------------------------------------------------------------------------------
    // Create Usuario
    public Boolean addUsuario(Usuario usuario) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "INSERT INTO public.Usuarios (nombre, apellido, email, contraseña, rol, verificado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContraseña());
            stmt.setString(5, usuario.getRol());
            stmt.setBoolean(6, usuario.getVerificado() != 0);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            postgreSQLHelper.disconnect();
        }
    }

    // Read Usuario
    public Usuario getUsuarioById(int id) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "SELECT * FROM public.Usuarios WHERE id = ?";
        Usuario usuario = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("rol"),
                        rs.getBoolean("verificado") ? 1 : 0
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
        return usuario;
    }

    // Update Usuario
    public int updateUsuario(Usuario usuario) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "UPDATE public.Usuarios SET nombre = ?, apellido = ?, email = ?, contraseña = ?, rol = ?, verificado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContraseña());
            stmt.setString(5, usuario.getRol());
            stmt.setBoolean(6, usuario.getVerificado() != 0);
            stmt.setInt(7, usuario.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            postgreSQLHelper.disconnect();
        }
    }

    // Delete Usuario
    public void deleteUsuario(Usuario usuario) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "DELETE FROM public.Usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
    }

    // Read All Usuarios
    public List<Usuario> getAllUsuarios() {
        Connection connection = postgreSQLHelper.getConnection();
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM public.Usuarios";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("rol"),
                        rs.getBoolean("verificado") ? 1 : 0
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
        return usuarios;
    }

    // Count Usuario
    public int getUsuariosCount() {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "SELECT COUNT(*) FROM public.Usuarios";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
        return 0;
    }
    // ----------------------------------------------------------------------------------------
    // Empleos CRUD ----------------------------------------------------------------------------------------
    // Create Empleo
    public Boolean addEmpleo(Empleo empleo) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "INSERT INTO public.Empleos (titulo, descripcion, ubicacion, salario, requisitos, reclutador_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, empleo.getTitulo());
            stmt.setString(2, empleo.getDescripcion());
            stmt.setString(3, empleo.getUbicacion());
            stmt.setString(4, empleo.getSalario());
            stmt.setString(5, empleo.getRequisitos());
            stmt.setInt(6, empleo.getReclutadorId());
            stmt.setTimestamp(7, empleo.getCreatedAt());
            stmt.setTimestamp(8, empleo.getUpdatedAt());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            postgreSQLHelper.disconnect();
        }
    }

    // Read Empleo by ID
    public Empleo getEmpleoById(int id) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "SELECT * FROM public.Empleos WHERE id = ?";
        Empleo empleo = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                empleo = new Empleo(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("ubicacion"),
                        rs.getString("salario"),
                        rs.getString("requisitos"),
                        rs.getInt("reclutador_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
        return empleo;
    }

    // Update Empleo
    public int updateEmpleo(Empleo empleo) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "UPDATE public.Empleos SET titulo = ?, descripcion = ?, ubicacion = ?, salario = ?, requisitos = ?, reclutador_id = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, empleo.getTitulo());
            stmt.setString(2, empleo.getDescripcion());
            stmt.setString(3, empleo.getUbicacion());
            stmt.setString(4, empleo.getSalario());
            stmt.setString(5, empleo.getRequisitos());
            stmt.setInt(6, empleo.getReclutadorId());
            stmt.setTimestamp(7, empleo.getUpdatedAt());
            stmt.setInt(8, empleo.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            postgreSQLHelper.disconnect();
        }
    }

    // Delete Empleo
    public void deleteEmpleo(int id) {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "DELETE FROM public.Empleos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
    }

    // Read All Empleos
    public List<Empleo> getAllEmpleos() {
        Connection connection = postgreSQLHelper.getConnection();
        List<Empleo> empleos = new ArrayList<>();
        String query = "SELECT * FROM public.Empleos ORDER BY id DESC";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empleo empleo = new Empleo(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("ubicacion"),
                        rs.getString("salario"),
                        rs.getString("requisitos"),
                        rs.getInt("reclutador_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                empleos.add(empleo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
        return empleos;
    }

    // Count Empleos
    public int getEmpleosCount() {
        Connection connection = postgreSQLHelper.getConnection();
        String query = "SELECT COUNT(*) FROM public.Empleos";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLHelper.disconnect();
        }
        return 0;
    }

}
