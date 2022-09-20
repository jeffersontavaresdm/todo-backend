package com.todo.utils;

import com.todo.annotation.EmailValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidation implements ConstraintValidator<EmailValidate, String> {

  /**
   * @Example: (blabla @ email.com) -> blabla = local, @, email.com = domínio
   * <p>
   * Restrições impostas na parte local do endereço de e-mail:
   * Permite valores numéricos de 0 a 9.
   * Tanto letras maiúsculas quanto minúsculas de a a z são permitidas.
   * São permitidos underline (_), hífen (-), e ponto (.)
   * Ponto não é permitido no início e no fim da parte local.
   * Pontos consecutivos não são permitidos.
   * Permitido um máximo de 64 caracteres.
   * <p>
   * Restrições impostas na parte de domínio do endereço de e-mail:
   * Permite valores numéricos de 0 a 9.
   * Permitimos letras maiúsculas e minúsculas de a a z.
   * Hífen (-) e ponto (.) não são permitidos no início e no final da parte de domínio.
   * Sem pontos consecutivos.
   */
  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    return Pattern
      .compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
      .matcher(email)
      .matches();
  }
}