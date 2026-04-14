package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class PessoaBO {

    private UUID id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    public PessoaBO() {
    }

    public PessoaBO(UUID id, String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        this.id = id != null ? id : UUID.randomUUID();
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        validar();
    }

    public void validar() {
        if (dataNascimento == null || Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            throw new DomainException("Erro: Idade mínima de 18 anos não atendida.");
        }
        if (cpf == null || cpf.length() != 11) {
            throw new DomainException("Erro: O CPF deve conter exatamente 11 dígitos.");
        }
        if (telefone == null || telefone.length() != 11 || !telefone.matches("\\d+")) {
            throw new DomainException("Erro: O telefone deve conter exatamente 11 dígitos numéricos.");
        }
        if (email == null || !email.contains("@")) {
            throw new DomainException("Erro: E-mail inválido, deve conter '@'.");
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}