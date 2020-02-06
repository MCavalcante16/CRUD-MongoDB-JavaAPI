package com.mauricio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.mauricio.dao.DependenteDAO;
import com.mauricio.dao.FuncionarioLimpezaDAO;
import com.mauricio.dao.PesquisadorDAO;
import com.mauricio.dao.SecretarioDAO;
import com.mauricio.dao.mongodb.FuncionarioLimpezaMongoDBDAO;
import com.mauricio.dao.mongodb.PesquisadorMongoDBDAO;
import com.mauricio.dao.mongodb.SecretarioMongoDBDAO;
import com.mauricio.model.Departamento;
import com.mauricio.model.Dependente;
import com.mauricio.model.Funcionario;
import com.mauricio.model.FuncionarioLimpeza;
import com.mauricio.model.Pesquisador;
import com.mauricio.model.Secretario;

public class DependenteController {

	private static Scanner sc1;
	
	public static void insert(DependenteDAO dao) {
		
		sc1 = new Scanner(System.in);
		
		Dependente entidade = new Dependente();
		
		System.out.println("Digite o nome:");
		String nome = sc1.nextLine();
		
		System.out.println("Digite o sexo:");
		String sexo = sc1.nextLine();
		
		System.out.println("Digite a data de nascimento:");
		LocalDate dataNascimento = LocalDate.parse(sc1.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		System.out.println("Digite o grau de parentesco:");
		String grauParentesco = sc1.nextLine();
		
		PesquisadorDAO daoP = new PesquisadorMongoDBDAO();
		SecretarioDAO daoS = new SecretarioMongoDBDAO();
		FuncionarioLimpezaDAO daoF = new FuncionarioLimpezaMongoDBDAO();
		
		List<Funcionario> funcionarios = new ArrayList<>();
		
		funcionarios.addAll(daoP.findAll());
		funcionarios.addAll(daoS.findAll());
		funcionarios.addAll(daoF.findAll());
		
		int pos = 0;
		
		for(Funcionario funcionario : funcionarios) {	
			System.out.println("Funcionario "+(pos+1));
			System.out.println("Id: " + funcionario.getId());
			System.out.println("Nome: " + funcionario.getNome());
			pos++;
		}
		daoP.close();
		daoS.close();
		daoF.close();
		
		System.out.println("Digite o funcionario:");
		Integer posicao = sc1.nextInt();
		
		entidade.setIdFuncionario(funcionarios.get(posicao-1).getId());
		entidade.setNome(nome);
		entidade.setSexo(sexo);
		entidade.setDataNascimento(dataNascimento);
		entidade.setGrauParentesco(grauParentesco);
		
		dao.insert(entidade);
		dao.close();

	}

	public static void getAll(DependenteDAO dao) {
		List<Dependente> list = dao.findAll();
		
		PesquisadorDAO daoP = new PesquisadorMongoDBDAO();
		SecretarioDAO daoS = new SecretarioMongoDBDAO();
		FuncionarioLimpezaDAO daoF = new FuncionarioLimpezaMongoDBDAO();
		
		for(Dependente entidade : list) {
			
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Data de Nascimento: " + entidade.getDataNascimento());
			System.out.println("Grau de Parentesco: " + entidade.getGrauParentesco());
			
			Pesquisador pesquisador = daoP.find(new ObjectId(entidade.getIdFuncionario().toString()));
			Secretario secretario = daoS.find(new ObjectId(entidade.getIdFuncionario().toString()));
			FuncionarioLimpeza funcionario = daoF.find(new ObjectId(entidade.getIdFuncionario().toString()));
			
			if(pesquisador != null) {
				System.out.println("Pesquisador: "+pesquisador.getNome());
			}else if(secretario != null) {
				System.out.println("Secretario: "+secretario.getNome());
			}else{
				System.out.println("Funcionario(Limpeza): "+ funcionario.getNome());
			}
			
		}
		daoP.close();
		daoS.close();
		daoF.close();
		dao.close();
	}

	public static void delete(DependenteDAO dao) {
		int i = 0;
		sc1 = new Scanner(System.in);
		List<Dependente> list = dao.findAll();
		for(Dependente entidade : list) {
			System.out.println("\n Dependente "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Data de Nascimento: " + entidade.getDataNascimento());
			System.out.println("Grau de Parentesco: " + entidade.getGrauParentesco());

			i++;
		}
		
		System.out.println("Digite o Dependente a ser excluido:");
		
		int posicao = sc1.nextInt();
		System.out.println(list.get(posicao-1).getId());
		
		dao.delete(new ObjectId(list.get(posicao-1).getId().toString()));
		dao.close();
	}
	
	public static void find(DependenteDAO dao) {
		PesquisadorDAO daoP = new PesquisadorMongoDBDAO();
		SecretarioDAO daoS = new SecretarioMongoDBDAO();
		FuncionarioLimpezaDAO daoF = new FuncionarioLimpezaMongoDBDAO();
		
		int pos = 0;
		sc1 = new Scanner(System.in);
		List<Dependente> list = dao.findAll();
		
		for(Dependente entidade : list) {
			System.out.println("\n Dependente "+(pos+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			pos++;
		}
		
		System.out.println("Digite o Dependente desejado:");
		int posicao = sc1.nextInt();
		
		Dependente dependente = dao.find(new ObjectId(list.get(posicao-1).getId().toString()));
		Pesquisador pesquisador = daoP.find(new ObjectId(dependente.getIdFuncionario().toString()));
		Secretario secretario = daoS.find(new ObjectId(dependente.getIdFuncionario().toString()));
		FuncionarioLimpeza funcionario = daoF.find(new ObjectId(dependente.getIdFuncionario().toString()));
		
		System.out.println("Id: " + dependente.getId());
		System.out.println("Nome: " + dependente.getNome());
		System.out.println("Sexo: " + dependente.getSexo());
		System.out.println("Data de Nascimento: " + dependente.getDataNascimento());
		System.out.println("Grau de Parentesco: " + dependente.getGrauParentesco());
		
		
		
		
		if(pesquisador != null) {
			System.out.println("Pesquisador: "+pesquisador.getNome());
			System.out.println("Nome: " + pesquisador.getNome());
			System.out.println("Endereco: " + pesquisador.getEndereco());
			System.out.println("Sexo: " + pesquisador.getSexo());
			System.out.println("Salario: "+pesquisador.getSalario());
			System.out.println("Area de Atuacao: "+pesquisador.getAreaAtuacao());

		}else if(secretario != null) {
			System.out.println("Secretario: "+secretario.getNome());
			System.out.println("Nome: " + secretario.getNome());
			System.out.println("Endereco: " + secretario.getEndereco());
			System.out.println("Sexo: " + secretario.getSexo());
			System.out.println("Salario: "+secretario.getSalario());
			System.out.println("Grau de Escolaridade: "+secretario.getGrauEscolaridade());
			

		}else{
			System.out.println("Funcionario(Limpeza): "+ funcionario.getNome());
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Endereco: " + funcionario.getEndereco());
			System.out.println("Sexo: " + funcionario.getSexo());
			System.out.println("Salario: "+funcionario.getSalario());
			System.out.println("Cargo: "+funcionario.getCargo());
			System.out.println("Jornada de Trabalho: "+funcionario.getJornadaTrabalho());
	
		}
		
		daoP.close();
		daoS.close();
		daoF.close();
		dao.close();
	}

}
