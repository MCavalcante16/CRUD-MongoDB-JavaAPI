package com.mauricio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.mauricio.dao.DepartamentoDAO;
import com.mauricio.dao.DependenteDAO;
import com.mauricio.dao.PesquisadorDAO;
import com.mauricio.dao.SecretarioDAO;
import com.mauricio.dao.mongodb.DepartamentoMongoDBDAO;
import com.mauricio.dao.mongodb.DependenteMongoDBDAO;
import com.mauricio.model.Departamento;
import com.mauricio.model.Dependente;
import com.mauricio.model.Pesquisador;
import com.mauricio.model.Secretario;

public class SecretarioController {
	
private static Scanner sc1;
	
	public static void insert(SecretarioDAO dao) {
		
		sc1 = new Scanner(System.in);
		
		Secretario entidade = new Secretario();
		
		System.out.println("Digite seu nome :");
		String nome = sc1.nextLine();
		
		System.out.println("Digite seu endereço :");
		String endereco = sc1.nextLine();

		
		System.out.println("Digite seu sexo :");
		String sexo = sc1.nextLine();
		
		
		System.out.println("Digite sua data de nascimento:");
		LocalDate nascimento =  LocalDate.parse(sc1.nextLine(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
		
		System.out.println("Digite o seu salario:");
		Double salario = sc1.nextDouble();
		sc1.nextLine();
		
		System.out.println("Digite seu grau de escolaridade:");
		String grauEscolaridade = sc1.nextLine();
		
		DepartamentoDAO daoD = new DepartamentoMongoDBDAO();
		List<Departamento> list = daoD.findAll();
		int pos = 0;
		for(Departamento dep : list) {	
			System.out.println("Departamento "+(pos+1));
			System.out.println("Id: " + dep.getId());
			System.out.println("Nome: " + dep.getNome());
			pos++;
		}
		daoD.close();
		
		System.out.println("Digite o departamento:");
		Integer posicao = sc1.nextInt();
		
		entidade.setIdDepartamento(list.get(posicao-1).getId());
		
		entidade.setNome(nome);
		entidade.setEndereco(endereco);
		entidade.setSexo(sexo);
		entidade.setDataNascimento(nascimento);
		entidade.setSalario(salario);
		entidade.setGrauEscolaridade(grauEscolaridade);
		
		dao.insert(entidade);
		dao.close();

	}

	public static void getAll(SecretarioDAO dao) {
		
		DepartamentoDAO daoD = new DepartamentoMongoDBDAO();
		
		List<Secretario> list = dao.findAll();
		for(Secretario entidade : list) {
			System.out.println("\n \nId: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Endereco: " + entidade.getEndereco());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Salario: "+entidade.getSalario());
			System.out.println("Grau de Escolaridade: "+entidade.getGrauEscolaridade());
			Departamento departamento = daoD.find(new ObjectId(entidade.getIdDepartamento().toString()));
			System.out.println("Departamento: "+departamento.getNome());
		
		}
		dao.close();
	}

	public static void delete(SecretarioDAO dao) {
		
		DependenteDAO daoDependente = new DependenteMongoDBDAO();
		List<Dependente> dependentes = daoDependente.findAll();
		
		int i = 0;
		sc1 = new Scanner(System.in);
		List<Secretario> list = dao.findAll();
		for(Secretario entidade : list) {
			System.out.println("\n Secretario "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Endereco: " + entidade.getEndereco());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Salario: "+entidade.getSalario());
			System.out.println("Grau de Escolaridade: "+entidade.getGrauEscolaridade());
			i++;
		}
		
		System.out.println("Digite o id do Secretario a ser excluido:");
		int posicao = sc1.nextInt();
		
		for(Dependente dependente : dependentes) {
			
			if(new ObjectId(list.get(posicao-1).getId().toString()).equals(dependente.getIdFuncionario())) {
				
				daoDependente.delete(new ObjectId(dependente.getId().toString()));
			
			}
		}

		System.out.println(list.get(posicao-1).getId());
		
		dao.delete(new ObjectId(list.get(posicao-1).getId().toString()));
		
		
		
		
		
		dao.close();
		daoDependente.close();
	}
	
	public static void find(SecretarioDAO dao) {
		
		DependenteDAO daoDependente = new DependenteMongoDBDAO();
		DepartamentoDAO daoDepartamento = new DepartamentoMongoDBDAO();
		
		int pos = 0;
		sc1 = new Scanner(System.in);
		List<Secretario> list = dao.findAll();
		List<Dependente> dependentes = daoDependente.findAll();
		
		for(Secretario entidade : list) {
			System.out.println("\n Secretario "+(pos+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			pos++;
		}
		
		
		
		
		System.out.println("Digite o id do Secretario desejado:");
		int posicao = sc1.nextInt();
		Secretario Secretario = dao.find(new ObjectId(list.get(posicao-1).getId().toString()));
		Departamento departamento = daoDepartamento.find(new ObjectId(Secretario.getIdDepartamento().toString()));


		System.out.println("Id: " + Secretario.getId());
		System.out.println("Nome: " + Secretario.getNome());
		System.out.println("Endereco: " + Secretario.getEndereco());
		System.out.println("Sexo: " + Secretario.getSexo());
		System.out.println("Salario: "+Secretario.getSalario());
		System.out.println("Grau de escolaridade: "+Secretario.getGrauEscolaridade());
		System.out.println("Departamento: "+departamento.getNome());
		
		System.out.println("\n \t Dependentes");
		for(Dependente dependente : dependentes) {
			
			if(Secretario.getId().equals(dependente.getIdFuncionario())) {
				
				System.out.println("\n\nId: " + dependente.getId());
				System.out.println("Nome: " + dependente.getNome());
				System.out.println("Sexo: " + dependente.getSexo());
				System.out.println("Data de Nascimento: " + dependente.getDataNascimento());
				System.out.println("Grau de Parentesco: " + dependente.getGrauParentesco());
			
			}
		}
	
		daoDepartamento.close();
		daoDependente.close();
		dao.close();
		
	}
	
}
