package com.crud.pessoa.Repositories;

import java.util.UUID;

public record UserRequest(UUID id, String nome,String email) {
}
