package pages;

import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import utils.PageBase;

public class ShoppingCartPage extends PageBase {

    public ShoppingCartPage(RemoteWebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public ShoppingCartPage screenshot() {
        super.screenshot();
        return this;
    }

    @FindBy(id = "open-categories-btn")
    private WebElement categorias;

    @FindBy(id = "category-all")
    private WebElement categoriaTodas;

    @FindBy(id = "category-0")
    private WebElement categoriaBebidas;

    @FindBy(id = "category-1")
    private WebElement categoriaDoce;

    @FindBy(id = "category-2")
    private WebElement categoriaSalgados;

    @FindBy(id = "category-3")
    private WebElement categoriaPratosQuentes;

    @FindBy(id = "add-product-3-btn")
    private WebElement produtoRissoleMedio;

    @FindBy(className = "sc-gZMcBi")
    private List<WebElement> itensDaLista;

    @FindBy(className = "sc-csuQGl")
    private List<WebElement> itensCarrinho;

    @FindBy(id = "cart-btn")
    private WebElement carrinho;

    @FindBy(id = "add-product-4-qtd")
    private WebElement brigadeiroMais;

    @FindBy(id = "add-product-3-qtd")
    public WebElement rissoleMedioMais;

    @FindBy(id = "remove-product-3-qtd")
    public WebElement rissoleMedioMenos;

    @FindBy(id = "add-product-5-qtd")
    private WebElement alfajorMais;

    @FindBy(id = "finish-checkout-button")
    private WebElement finaliza;

    @FindBy(className = "sc-dNLxif")
    private WebElement finalizacaoMensagem;

    @FindBy(id = "price-total-checkout")
    private WebElement valorTotal;

    @FindBy(className = "close-modal")
    private WebElement fechar;

    public ShoppingCartPage acessarCategorias() {
        categorias.click();
        return this;
    }

    public ShoppingCartPage acessarCategoriaTodos() {
        categoriaTodas.click();
        return this;
    }

    public ShoppingCartPage acessarCategoriaBebidas() {
        categoriaBebidas.click();
        return this;
    }

    public ShoppingCartPage acessarCategoriaDoce() {
        categoriaDoce.click();
        return this;
    }

    public ShoppingCartPage adicionarRissoleMedio() {
        produtoRissoleMedio.click();
        return this;
    }

    public ShoppingCartPage adicionarTodos() {
        for (WebElement item : itensDaLista) {
            item.click();
        }
        return this;
    }

    public ShoppingCartPage acessarCarrinho() {
        carrinho.click();
        return this;
    }

    public ShoppingCartPage aumentarQuantidadeBrigadeiro(Integer quantidade) {
        for (int i = 0; i < quantidade; i++) {
            brigadeiroMais.click();
        }
        return this;
    }

    public ShoppingCartPage aumentarQuantidadeRisoleMedio(Integer quantidade) {
        for (int i = 0; i < quantidade; i++) {
            rissoleMedioMais.click();
        }
        return this;
    }

    public ShoppingCartPage diminuirQuantidadeRisoleMedio(Integer quantidade) {
        for (int i = 0; i < quantidade; i++) {
            rissoleMedioMenos.click();
        }
        return this;
    }

    public ShoppingCartPage validaValor() throws Exception {
        Double valorCompra = 0d;

        for (WebElement item : itensCarrinho) {
            WebElement quantidadeElement = item.findElement(By.className("dKbPQN"));
            Integer quantidade = Integer.parseInt(quantidadeElement.getText());

            WebElement valorUnitarioElement = item.findElement(By.className("sc-dVhcbM"));
            Double valorUnitario = getDoubleByReal(valorUnitarioElement.getText());

            valorCompra += quantidade * valorUnitario;
        }

        Double valorTotalCompra = getDoubleByReal(valorTotal.getText());

        passed = (valorTotalCompra - valorCompra) == 0;

        return this;
    }

    public ShoppingCartPage finalizarCompra() {
        finaliza.click();
        return this;
    }

    public ShoppingCartPage validarMensagem() {
        passed = finalizacaoMensagem.getText().equals("Pedido realizado com sucesso!");
        return this;
    }

    public ShoppingCartPage fecharMensagem() {
        fechar.click();
        return this;
    }

    private Double getDoubleByReal(String valor) throws Exception {
        String valorFormatado = valor.replace("R$", "").trim();
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        Number valorUnitario = decimalFormat.parse(valorFormatado);
        return valorUnitario.doubleValue();
    }
}