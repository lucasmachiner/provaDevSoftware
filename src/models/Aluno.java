package models;

public class Aluno {
    private int Matricula;
    private String Nome;
    private double Nota;

    public Aluno(int matricula, String nome, double nota) {
        this.Matricula = matricula;
        this.Nome = nome;
        this.Nota = nota;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public double getNota() {
        return Nota;
    }

    public void setNota(double nota) {
        Nota = nota;
    }

}
