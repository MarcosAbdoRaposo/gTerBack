package com.example.gter.service;

import com.example.gter.model.Terceirizado;
import com.example.gter.repository.TerceirizadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TerceirizadoServiceTest {

    @Mock
    private TerceirizadoRepository terceirizadoRepository;

    @InjectMocks
    private TerceirizadoService terceirizadoService;

    private UUID idExistente;
    private Terceirizado terceirizadoBase;

    @BeforeEach
    void setUp() {
        idExistente = UUID.randomUUID();
        terceirizadoBase = new Terceirizado(
                "Lucas Almeida",
                "10101010101",
                new Date(),
                "M",
                "Rua XV de Novembro, 450",
                "Centro",
                "Curitiba",
                "PR",
                "Brasil",
                "80020310",
                "lucas.almeida@gter.com"
        );
        terceirizadoBase.setIdeTerceirizado(idExistente);
    }

    // =========================================================================
    // TESTES DO MÉTODO: atualizar (100% dos 4 caminhos da Complexidade Ciclomática)
    // =========================================================================

    @Test
    @DisplayName("Caminho 1: Deve lançar exceção ao tentar atualizar ID inexistente")
    void atualizar_quandoIdNaoExiste_deveLancarRuntimeException() {
        // Arrange
        UUID idInexistente = UUID.randomUUID();
        when(terceirizadoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> terceirizadoService.atualizar(idInexistente, terceirizadoBase)
        );

        assertEquals("Terceirizado não encontrado com o ID: " + idInexistente, exception.getMessage());
        verify(terceirizadoRepository, never()).existsByNumCPF(any());
        verify(terceirizadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caminho 2: Deve atualizar com sucesso quando o CPF permanece o mesmo")
    void atualizar_quandoCpfNaoAlterado_deveAtualizarESalvarTerceirizado() {
        // Arrange
        Terceirizado dadosAtualizados = new Terceirizado(
                "Lucas Almeida Atualizado",
                "10101010101", // Mesmo CPF
                new Date(),
                "M",
                "Av. Paulista, 1000",
                "Bela Vista",
                "São Paulo",
                "SP",
                "Brasil",
                "01310100",
                "lucas.novo@gter.com"
        );
        when(terceirizadoRepository.findById(idExistente)).thenReturn(Optional.of(terceirizadoBase));
        when(terceirizadoRepository.save(any(Terceirizado.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Terceirizado resultado = terceirizadoService.atualizar(idExistente, dadosAtualizados);

        // Assert
        assertNotNull(resultado);
        assertEquals(idExistente, resultado.getIdeTerceirizado());
        assertEquals("Lucas Almeida Atualizado", resultado.getNomTerceirizado());
        assertEquals("Av. Paulista, 1000", resultado.getNomLogradouro());
        verify(terceirizadoRepository, never()).existsByNumCPF(any());
        verify(terceirizadoRepository, times(1)).save(dadosAtualizados);
    }

    @Test
    @DisplayName("Caminho 3: Deve lançar exceção quando novo CPF já pertence a outro registro")
    void atualizar_quandoNovoCpfJaExisteEmOutroRegistro_deveLancarRuntimeException() {
        // Arrange
        Terceirizado dadosComCpfDuplicado = new Terceirizado();
        dadosComCpfDuplicado.setNomTerceirizado("Lucas Almeida");
        dadosComCpfDuplicado.setNumCPF("20202020202"); // CPF diferente já em uso

        when(terceirizadoRepository.findById(idExistente)).thenReturn(Optional.of(terceirizadoBase));
        when(terceirizadoRepository.existsByNumCPF("20202020202")).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> terceirizadoService.atualizar(idExistente, dadosComCpfDuplicado)
        );

        assertEquals("O CPF informado já está em uso por outro terceirizado.", exception.getMessage());
        verify(terceirizadoRepository, times(1)).existsByNumCPF("20202020202");
        verify(terceirizadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caminho 4: Deve atualizar com sucesso quando CPF é alterado para um CPF livre")
    void atualizar_quandoNovoCpfNaoExiste_deveAtualizarESalvarTerceirizado() {
        // Arrange
        Terceirizado dadosComNovoCpf = new Terceirizado();
        dadosComNovoCpf.setNomTerceirizado("Lucas Almeida");
        dadosComNovoCpf.setNumCPF("30303030303"); // Novo CPF disponível

        when(terceirizadoRepository.findById(idExistente)).thenReturn(Optional.of(terceirizadoBase));
        when(terceirizadoRepository.existsByNumCPF("30303030303")).thenReturn(false);
        when(terceirizadoRepository.save(any(Terceirizado.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Terceirizado resultado = terceirizadoService.atualizar(idExistente, dadosComNovoCpf);

        // Assert
        assertNotNull(resultado);
        assertEquals(idExistente, resultado.getIdeTerceirizado());
        assertEquals("30303030303", resultado.getNumCPF());
        verify(terceirizadoRepository, times(1)).existsByNumCPF("30303030303");
        verify(terceirizadoRepository, times(1)).save(dadosComNovoCpf);
    }

    // =========================================================================
    // TESTES DO MÉTODO: salvar (Cobertura de Caminhos + Casos Caixa-Preta)
    // =========================================================================

    @Test
    @DisplayName("Deve salvar novo terceirizado quando ID é nulo e CPF não existe")
    void salvar_quandoNovoTerceirizadoComCpfUnico_devePersistirComSucesso() {
        // Arrange
        Terceirizado novo = new Terceirizado(
                "Marina Rocha",
                "20202020202",
                new Date(),
                "F",
                null, null, null, null, null, null, null
        );
        when(terceirizadoRepository.existsByNumCPF("20202020202")).thenReturn(false);
        when(terceirizadoRepository.save(novo)).thenAnswer(inv -> {
            novo.setIdeTerceirizado(UUID.randomUUID());
            return novo;
        });

        // Act
        Terceirizado salvo = terceirizadoService.salvar(novo);

        // Assert
        assertNotNull(salvo.getIdeTerceirizado());
        assertEquals("20202020202", salvo.getNumCPF());
        verify(terceirizadoRepository, times(1)).save(novo);
    }

    @Test
    @DisplayName("Deve lançar exceção ao salvar novo terceirizado com CPF duplicado")
    void salvar_quandoNovoTerceirizadoComCpfDuplicado_deveLancarRuntimeException() {
        // Arrange
        Terceirizado duplicado = new Terceirizado();
        duplicado.setNumCPF("10101010101");
        when(terceirizadoRepository.existsByNumCPF("10101010101")).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> terceirizadoService.salvar(duplicado)
        );

        assertEquals("Já existe um terceirizado cadastrado com o CPF: 10101010101", exception.getMessage());
        verify(terceirizadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve salvar diretamente sem validar CPF quando ID já está preenchido")
    void salvar_quandoIdJaInformado_deveIgnorarChecagemDeCpfESalvar() {
        // Arrange
        when(terceirizadoRepository.save(terceirizadoBase)).thenReturn(terceirizadoBase);

        // Act
        Terceirizado resultado = terceirizadoService.salvar(terceirizadoBase);

        // Assert
        assertEquals(idExistente, resultado.getIdeTerceirizado());
        verify(terceirizadoRepository, never()).existsByNumCPF(any());
        verify(terceirizadoRepository, times(1)).save(terceirizadoBase);
    }

    // =========================================================================
    // TESTES DOS MÉTODOS DE CONSULTA E EXCLUSÃO
    // =========================================================================

    @Test
    @DisplayName("Deve retornar terceirizado ao buscar por CPF existente")
    void buscarPorCpf_quandoCpfExiste_deveRetornarTerceirizado() {
        // Arrange
        when(terceirizadoRepository.findByNumCPF("10101010101")).thenReturn(Optional.of(terceirizadoBase));

        // Act
        Terceirizado encontrado = terceirizadoService.buscarPorCpf("10101010101");

        // Assert
        assertEquals("Lucas Almeida", encontrado.getNomTerceirizado());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar por CPF inexistente")
    void buscarPorCpf_quandoCpfNaoExiste_deveLancarRuntimeException() {
        // Arrange
        when(terceirizadoRepository.findByNumCPF("99999999999")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> terceirizadoService.buscarPorCpf("99999999999")
        );

        assertEquals("Terceirizado não encontrado com o CPF: 99999999999", exception.getMessage());
    }

    @Test
    @DisplayName("Deve buscar terceirizados por nome parcial ignorando maiúsculas/minúsculas")
    void buscarPorNome_quandoNomeParcialInformado_deveRetornarListaCorrespondente() {
        // Arrange
        when(terceirizadoRepository.findByNomTerceirizadoContainingIgnoreCase("lucas"))
                .thenReturn(List.of(terceirizadoBase));

        // Act
        List<Terceirizado> lista = terceirizadoService.buscarPorNome("lucas");

        // Assert
        assertEquals(1, lista.size());
        assertEquals("Lucas Almeida", lista.get(0).getNomTerceirizado());
    }

    @Test
    @DisplayName("Deve buscar terceirizados distintos por contrato")
    void buscarPorContrato_quandoContratoExiste_deveRetornarListaDistintaOrdenada() {
        // Arrange
        UUID ideContrato = UUID.randomUUID();
        when(terceirizadoRepository.findDistinctByContrato(ideContrato))
                .thenReturn(List.of(terceirizadoBase));

        // Act
        List<Terceirizado> lista = terceirizadoService.buscarPorContrato(ideContrato);

        // Assert
        assertEquals(1, lista.size());
        verify(terceirizadoRepository, times(1)).findDistinctByContrato(ideContrato);
    }

    @Test
    @DisplayName("Deve excluir terceirizado quando ID existe")
    void deletar_quandoIdExiste_deveRemoverRegistro() {
        // Arrange
        when(terceirizadoRepository.findById(idExistente)).thenReturn(Optional.of(terceirizadoBase));

        // Act
        terceirizadoService.deletar(idExistente);

        // Assert
        verify(terceirizadoRepository, times(1)).delete(terceirizadoBase);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar excluir ID inexistente")
    void deletar_quandoIdNaoExiste_deveLancarRuntimeException() {
        // Arrange
        UUID idInexistente = UUID.randomUUID();
        when(terceirizadoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> terceirizadoService.deletar(idInexistente));
        verify(terceirizadoRepository, never()).delete(any());
    }
}