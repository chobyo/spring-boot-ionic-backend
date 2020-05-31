package com.chobyo.cursomc.domains.enums;

public enum TipoCliente {
	PESSOAFISICA('F', "Pessoa Física"), PESSOAJURIDICA('J', "Pessoa Juridica");

	private char cod;
	private String descricao;

	private TipoCliente(char cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public char getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(char cod)
	{
		if (cod == Character.MIN_VALUE) {
			return null;
		}
		
		for(TipoCliente c : TipoCliente.values())
		{
			if (cod == c.cod)
			{
				return c;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
