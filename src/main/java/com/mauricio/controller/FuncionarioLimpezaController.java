package com.mauricio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.mauricio.dao.DepartamentoDAO;
import com.mauricio.dao.DependenteDAO;
import com.mauricio.dao.FuncionarioLimpezaDAO;
import com.mauricio.dao.PesquisadorDAO;
import com.mauricio.dao.mongodb.DepartamentoMongoDBDAO;
import com.mauricio.dao.mongodb.DependenteMongoDBDAO;
import com.mauricio.model.Departamento;
import com.mauricio.model.Dependente;
import com.mauricio.model.FuncionarioLimpeza;
import com.mauricio.model.Pesquisador;

public class FuncionarioLimpezaController {
	
private static Scanner sc1;
	
	public static void insert(FuncionarioLimpezaDAO dao) {
		
		sc1 = new Scanner(System.in);
		
		FuncionarioLimpeza entidade = new FuncionarioLimpeza();
		
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
		
		System.out.println("Digite o seu cargo:");
		String cargo = sc1.nextLine();
		
		System.out.println("Digite a sua jornada de trabalho:");
		String jornadaTrabalho = sc1.nextLine();
		
		entidade.setNome(nome);
		entidade.setEndereco(endereco);
		entidade.setSexo(sexo);
		entidade.setDataNascimento(nascimento);
		entidade.setSalario(salario);
		entidade.setCargo(cargo);
		entidade.setJornadaTrabalho(jornadaTrabalho);
		
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
		
		dao.insert(entidade);
		dao.close();

	}

	public static void getAll(FuncionarioLimpezaDAO dao) {
		List<FuncionarioLimpeza> list = dao.findAll();
		
		DepartamentoDAO daoD = new DepartamentoMongoDBDAO();
		
		for(FuncionarioLimpeza entidade : list) {
			System.out.println("\n \nId: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Endereco: " + entidade.getEndereco());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Salario: "+entidade.getSalario());
			System.out.println("Cargo: "+entidade.getCargo());
			Departamento departamento = daoD.find(new ObjectId(entidade.getIdDepartamento().toString()));
			System.out.println("Departamento: "+departamento.getNome());
			System.out.println("Jornada de Trabalho: "+entidade.getJornadaTrabalho());
		
		}
		dao.close();
	}

	public static void delete(FuncionarioLimpezaDAO dao) {
		
		int i = 0;
		sc1 = new Scanner(System.in);
		
		List<FuncionarioLimpeza> list = dao.findAll();
		DependenteDAO daoDependente = new DependenteMongoDBDAO();
		List<Dependente> dependentes = daoDependente.findAll();
		
		for(FuncionarioLimpeza entidade : list) {
			System.out.println("\n Funcionario "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Endereco: " + entidade.getEndereco());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Salario: "+entidade.getSalario());
			System.out.println("Cargo: "+entidade.getCargo());
			System.out.println("Jornada de Trabalho: "+entidade.getJornadaTrabalho());
			i++;
		}
		
		System.out.println("Digite o id do Funcionario da Limpeza a ser excluido:");
		
		int posicao = sc1.nextInt();
		System.out.println(list.get(posicao-1).getId());
		
		for(Dependente dependente : dependentes) {
			
			if(new ObjectId(list.get(posicao-1).getId().toString()).equals(dependente.getIdFuncionario())) {
				
				daoDependente.delete(new ObjectId(dependente.getId().toString()));
			
			}
		}
		
		
		dao.delete(new ObjectId(list.get(posicao-1).getId().toString()));
		daoDependente.close();
		dao.close();
		
	}
	
	public static void find(FuncionarioLimpezaDAO dao) {
		
		DependenteDAO daoDependente = new DependenteMongoDBDAO();
		DepartamentoDAO daoDepartamento = new DepartamentoMongoDBDAO();
		
		int pos = 0;
		sc1 = new Scanner(System.in);
		List<FuncionarioLimpeza> list = dao.findAll();
		List<Dependente> dependentes = daoDependente.findAll();
		
		for(FuncionarioLimpeza entidade : list) {
			System.out.println("\n FuncionarioLimpeza "+(pos+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			pos++;
		}
		
		
		
		
		System.out.println("Digite o id do FuncionarioLimpeza desejado:");
		int posicao = sc1.nextInt();
		FuncionarioLimpeza FuncionarioLimpeza = dao.find(new ObjectId(list.get(posicao-1).getId().toString()));
		Departamento departamento = daoDepartamento.find(new ObjectId(FuncionarioLimpeza.getIdDepartamento().toString()));


		System.out.println("Id: " + FuncionarioLimpeza.getId());
		System.out.println("Nome: " + FuncionarioLimpeza.getNome());
		System.out.println("Endereco: " + FuncionarioLimpeza.getEndereco());
		System.out.println("Sexo: " + FuncionarioLimpeza.getSexo());
		System.out.println("Salario: "+FuncionarioLimpeza.getSalario());
		System.out.println("Cargo: "+FuncionarioLimpeza.getCargo());
		System.out.println("Jornada de Trabalho: "+FuncionarioLimpeza.getJornadaTrabalho());
		System.out.println("Departamento: "+departamento.getNome());
		
		System.out.println("\n \t Dependentes");
		for(Dependente dependente : dependentes) {
			
			if(FuncionarioLimpeza.getId().equals(dependente.getIdFuncionario())) {
				
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
