package tests;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import pages.ShoppingCartPage;

import utils.TestBase;

public class ShoppingCartTest extends TestBase {
    private ShoppingCartPage shoppingCartPage;

    @Before
    public void setup() {
        this.shoppingCartPage = new ShoppingCartPage(getWebDriver());
        this.shoppingCartPage.setScreenshotTaker(this);
    }

    @Test
    public void Quest1() throws Exception {
        openUrl("http://shopcart-challenge.4all.com");

        shoppingCartPage
                .screenshot()
                .acessarCategorias()
                .screenshot()
                .acessarCategoriaDoce()
                .screenshot()
                .adicionarTodos()
                .screenshot()
                .acessarCategorias()
                .screenshot()
                .acessarCategoriaTodos()
                .screenshot()
                .acessarCarrinho()
                .screenshot()
                .aumentarQuantidadeBrigadeiro(4)
                .screenshot()
                .finalizarCompra()
                .screenshot()
                .validarMensagem()
                .screenshot()
                .fecharMensagem()
                .screenshot();

        assertTrue(shoppingCartPage.getPassed());

        close();
    }

    @Test
    public void Quest2() throws Exception {
        openUrl("http://shopcart-challenge.4all.com");

        shoppingCartPage
                .screenshot()
                .acessarCategorias()
                .screenshot()
                .acessarCategoriaBebidas()
                .screenshot()
                .adicionarTodos()
                .screenshot()
                .acessarCategorias()
                .screenshot()
                .acessarCategoriaTodos()
                .screenshot()
                .adicionarRissoleMedio()
                .screenshot()
                .acessarCarrinho()
                .screenshot()
                .aumentarQuantidadeRisoleMedio(9)
                .screenshot()
                .diminuirQuantidadeRisoleMedio(5)
                .screenshot()
                .validaValor()
                .screenshot()
                .finalizarCompra()
                .screenshot()
                .validarMensagem()
                .screenshot()
                .fecharMensagem()
                .screenshot();

        assertTrue(shoppingCartPage.getPassed());

        close();
    }
}