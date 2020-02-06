package com.mauricio.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.mauricio.dao.DepartamentoDAO;
import com.mauricio.model.Departamento;


public class DepartamentoController {

	private static Scanner sc1;
	
	public static void insert(DepartamentoDAO dao) {
		
		sc1 = new Scanner(System.in);
		
		Departamento entidade = new Departamento();
		System.out.println("Digite o nome do Departamento:");
		String nome = sc1.nextLine();
		entidade.setNome(nome);
		dao.insert(entidade);
		dao.close();

	}

	public static void getAll(DepartamentoDAO dao) {
		List<Departamento> list = dao.findAll();
		for(Departamento entidade : list) {
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
		
		}
		dao.close();
	}

	public static void delete(DepartamentoDAO dao) {
		int i = 0;
		sc1 = new Scanner(System.in);
		List<Departamento> list = dao.findAll();
		for(Departamento entidade : list) {
			System.out.println("\n Departamento "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			i++;
		}
		
		System.out.println("Digite o id do Departamento a ser excluido:");
		
		int posicao = sc1.nextInt();
		System.out.println(list.get(posicao-1).getId());
		
		dao.delete(new ObjectId(list.get(posicao-1).getId().toString()));
		dao.close();
	}

}
