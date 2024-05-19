package models;

import java.sql.Timestamp;

public class Empleo {
    private int id;
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String salario;
    private String requisitos;
    private int reclutadorId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Empleo(int id, String titulo, String descripcion, String ubicacion, String salario, String requisitos, int reclutadorId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.salario = salario;
        this.requisitos = requisitos;
        this.reclutadorId = reclutadorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Empleo(String titulo, String descripcion, String ubicacion, String salario, String requisitos, int reclutadorId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.salario = salario;
        this.requisitos = requisitos;
        this.reclutadorId = reclutadorId;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public int getReclutadorId() {
        return reclutadorId;
    }

    public void setReclutadorId(int reclutadorId) {
        this.reclutadorId = reclutadorId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
