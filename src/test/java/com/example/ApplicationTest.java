package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

class ApplicationTest {

    WebDriver driver = null;
    String web = "";
    private static WebDriver miDriver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); // Configurar el controlador de Chrome
        miDriver = new ChromeDriver(); // Inicializar el WebDriver
    }

    @BeforeEach
    void lalala() {
        //miDriver = new ChromeDriver();
    }

    @AfterEach
    void lololo() {
        /*
         * try {
         * Thread.sleep(1000);
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */

        miDriver.quit();
    }

    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    void driverManagerChrome() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Test
    void driverManagerFirefox() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");
    }

    @Test
    void driverManagerOpera() {
        WebDriverManager.operadriver().setup();
        WebDriver driver = new OperaDriver();
        driver.get("https://www.google.com");
    }

    @Test
    void testWiki() {

        /*
         * String driverPath =
         * "/Users/nayvir.zerpa/glb/WebAutomation copy/src/utils/chromedriver";
         * System.setProperty("webdriver.chrome.driver", driverPath);
         */
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://es.wikipedia.org/wiki/Wikipedia:Portada");
        WebElement footerAbout = driver.findElement(By.id("footer-places-about"));
        // WebElement footerLink =
        // driver.findElement(By.cssSelector(".footer-sidebar-text"));
        // searchInput.sendKeys("Selenium");
        assertEquals(footerAbout.getText(), "Acerca de Wikipedia");
        driver.close();
    }

    @Test
    void testNavegacion() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.navigate().to("https://openai.com/");

        // maximiza navegador
        driver.manage().window().maximize();
        // retrocede pagina anterior
        driver.navigate().back();
        // avanza siguiten pagina
        driver.navigate().forward();

        driver.navigate().back();

        driver.close();

    }

    @Test
    void testIntegrador() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.navigate().to("https://www.google.com");

        // capturar tamaño original ventana
        int widthOrigen = driver.manage().window().getSize().getWidth();
        int heightOrigen = driver.manage().window().getSize().getHeight();

        // maximiza navegador
        driver.manage().window().maximize();

        driver.navigate().to("https://openai.com");

        // reducir 50% tamaño ventana
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        driver.manage().window().setSize(new Dimension(width / 2, height / 2));

        // centrar pantalla
        int x = (width - driver.manage().window().getSize().getWidth()) / 2;
        int y = (height - driver.manage().window().getSize().getHeight()) / 2;
        driver.manage().window().setPosition(new Point(x, y));

        // navegar a wikipedia
        driver.navigate().to("https://es.wikipedia.org");

        // restaurar ventana al tamaño original
        driver.manage().window().setSize(new Dimension(widthOrigen, heightOrigen));

        // cierra ventana
        driver.close();
    }

    @Test
    void practicaClassNameTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        web = "https://egg.live/es-ar/";

        driver.navigate().to(web);

        String claseBoton = "button";
        int cuentaBotones = driver.findElements(By.className(claseBoton)).size();

        System.out.println("Cantidad clase button: " + cuentaBotones);

        web = "https://www.thisisfeliznavidad.com/productos/invader-sweater/";
        driver.navigate().to(web);

        String claseImagenes = "product-slider-image";
        int cuentaImagenes = driver.findElements(By.className(claseImagenes)).size();

        System.out.println("Cantidad clase product-slider-image: " + cuentaImagenes);

    }

    @Test
    void practicaClassNameTestEj3() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        web = "https://www.thisisfeliznavidad.com/por-producto/retro-sweaters/?mpage=9";
        driver.navigate().to(web);

        String claseStarWar = "Star Wars";

        // "js-item-image";

        String claseProductos = "item-image-primary";// "item-image-secondary"

        // obtenemos lista de todos los productos (imagenes)

        List<WebElement> listaProductos = driver.findElements(By.className(claseProductos));

        System.out.println("Cantidad productos: " + listaProductos.size());

        int contarSW = 0;
        for (WebElement p : listaProductos) {

            if (p.getAttribute("alt").contains(claseStarWar)) {
                System.out.println(p.getAttribute("alt"));
                contarSW++;
            }
        }

        System.out.println("Hay " + contarSW + " productos Star War");

    }

    @Test
    void searchByTagAndId() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        web = "https://www.google.com.ar";
        driver.navigate().to(web);
        WebElement googleSearch = driver.findElement(By.cssSelector("textarea#APjFqb"));
        System.out.println(googleSearch);
    }

    @Test
    void searchByTagAndAttribute() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        web = "https://www.twitter.com";
        driver.navigate().to(web);
        WebElement twitter = driver.findElement(By.cssSelector("a[data-testid=signupButton]"));
        System.out.println(twitter);
    }

    @Test
    void searchByTagAndClase() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/");
        driver.findElement(By.cssSelector("a.HeaderMenu-link--sign-in")).click();
    }

    @Test
    void searchByTagClaseAndAttribute() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        WebElement signup = driver
                .findElement(By.cssSelector("a.font-sans[data-tracking-control-name=homepage-basic_forgot_password]"));
        System.out.println(signup);
    }

    @Test
    void searchByTagAndClase2() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        web = "https://www.stackoverflow.com";
        driver.navigate().to(web);
        driver.findElement(By.cssSelector("a.js-gps-track")).click();
    }

    @Test
    void ejercicioCssSelector1() {
        miDriver.get("https://www.google.com");
        // TAG + ID
        WebElement buscador = miDriver.findElement(By.cssSelector("textarea#APjFqb"));

        Assertions.assertNotEquals(null, buscador);
    }

    @Test
    void ejercicioCssSelector2() {
        miDriver.get("https://github.com/");
        // TAG + CLASSNAME
        WebElement elBotonMagicoDelGit = miDriver.findElement(By.cssSelector("button.HeaderMenu-link"));
        elBotonMagicoDelGit.click();

        WebElement enlaceReLoco = miDriver.findElement(By.cssSelector("a.HeaderMenu-dropdown-link"));
        enlaceReLoco.click();

        Assertions.assertNotEquals(null, elBotonMagicoDelGit);
        Assertions.assertNotEquals(null, enlaceReLoco);
    }

    @Test
    void ejercicioCssSelector3() {
        miDriver.get("https://twitter.com/");
        // TAG + ATRIBUTE
        WebElement elBotonDeSinUP = miDriver.findElement(By.cssSelector("a[data-testid=signupButton]"));
        elBotonDeSinUP.click();
        Assertions.assertNotEquals(null, elBotonDeSinUP);
    }

    @Test
    void ejercicioCssSelector4() {
        miDriver.get("https://www.linkedin.com/");
        // TAG + CLASSNAME + ATRIBUTE
        WebElement elBotonDelAlzheimer = miDriver.findElement(By.cssSelector(
                "a.sign-in-form__forgot-password--full-width[data-id=sign-in-form__forgot-password]"));
        elBotonDelAlzheimer.click();

        Assertions.assertNotEquals(null, elBotonDelAlzheimer);
    }

    @Test
    void ejercicioCssSelector5() {
        miDriver.get("https://medium.com/");

        // XPATH
        String xpathDelArticulo = "//*[@id='root']/div/div[4]/div[1]/div/div/div/div[2]/div/div[1]/div/div/div[2]/div[2]/a";
        WebElement elArticuloConRutaFea = miDriver.findElement(By.xpath(xpathDelArticulo));
        elArticuloConRutaFea.click();

        Assertions.assertNotEquals(null, elArticuloConRutaFea);
    }

    @Test
    // 6. Etiqueta + Clase: Encuentra y haz clic en el enlace "Contact" en el pie de
    // página del sitio Stack Overflow.
    void ejercicioCssSelector6() {
        miDriver.get("https://es.stackoverflow.com/");
        // TAG + CLASSNAME
        List<WebElement> anchorFeoDeS = miDriver.findElements(By.cssSelector("a.js-gps-track"));
        WebElement contactoDelStackOverflow = anchorFeoDeS.get(46);
        contactoDelStackOverflow.click();
        Assertions.assertNotEquals(null, contactoDelStackOverflow);
    }

    @Test
    // 7. Etiqueta + ID: Encuentra y muestra la descripción del primer producto en
    // la página de inicio de Amazon.
    void ejercicioCssSelector7() {
        miDriver.get("https://www.amazon.com/");
        // TAG + ID
        // List<WebElement> carruselDeAmazon =
        // miDriver.findElements(By.cssSelector("div#685bf823-01f6-4791-8e4f-27661a121844"));
        // WebElement contactoDelStackOverflow =
        // miDriver.findElements(By.cssSelector("div#685bf823-01f6-4791-8e4f-27661a121844")).get(1);
        WebElement elemento = miDriver.findElement(By.cssSelector(
                "img.product-image[alt='SAMSUNG 980 PRO SSD with Heatsink 2TB PCIe Gen 4 NVMe M.2 Internal Solid State Drive + 2mo Adobe CC Photography, Heat...']"));

        String descripcion = elemento.getAttribute("alt");
        Assertions.assertEquals(
                "SAMSUNG 980 PRO SSD with Heatsink 2TB PCIe Gen 4 NVMe M.2 Internal Solid State Drive + 2mo Adobe CC Photography, Heat...",
                descripcion);
    }

    @Test
    void waitForSearchBarInTwitter() {
        miDriver.get("https://twitter.com/");
        miDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String signIn = "#react-root > div > div > div.css-1dbjc4n.r-13qz1uu.r-417010 > main > div > div > div.css-1dbjc4n.r-791edh.r-1euycsn.r-tv6buo > div.css-1dbjc4n.r-1777fci.r-1qmwkkh.r-nsbfu8 > div > div.css-1dbjc4n > div.css-1dbjc4n.r-2o02ov > a > div > span > span";
        String sendPw = "css-1dbjc4n.r-13qz1uu.r-417010";
        miDriver.findElement(By.cssSelector(signIn)).click();
        miDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        miDriver.findElement(By.cssSelector("input.r-30o5oe")).sendKeys("INGRESARMAILVALIDO@hotmail.com");

        List<WebElement> nextButton = miDriver.findElements(By.cssSelector(
                "div.css-901oao.r-1awozwy.r-6koalj.r-18u37iz.r-16y2uox.r-37j5jr.r-a023e6.r-b88u0q.r-1777fci.r-rjixqe.r-bcqeeo.r-q4m81j.r-qvutc0[dir=ltr]"));
        int i = 0;
        for (WebElement iterable_element : nextButton) {
            System.out.println("Ingresa al for");
            if (i == 2) {
                iterable_element.click();
                miDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                System.out.println("");
                break;
            }
            i++;
        }
        miDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String telNumber = "1136918040";
        String telBox = "input[autocapitalize=\"none\"]";
        miDriver.findElement(By.cssSelector(telBox)).sendKeys(telNumber);
        String buttonNext = "div.css-18t94o4[data-testid=ocfEnterTextNextButton]";
        miDriver.findElement(By.cssSelector(buttonNext)).click();
        miDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String pw = "input.r-30o5oe.r-1niwhzg.r-17gur6a.r-1yadl64.r-deolkf.r-homxoj.r-poiln3.r-7cikom.r-1ny4l3l.r-t60dpp.r-1dz5y72.r-fdjqy7.r-13qz1uu";
        miDriver.findElement(By.cssSelector(pw)).sendKeys("PwFalsa123");
        String init = "div[data-testid=LoginForm_Login_Button]";
        miDriver.findElement(By.cssSelector(init)).click();

    }

    @Test
    void waitForLoadMoreButtonInMedium() {
        miDriver.get("https://medium.com/");
    }

    @Test
    void waitForSearchBarInTwitter2() throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // Configurar el controlador de Chrome
        WebDriver miDriver = new ChromeDriver(); // Inicializar el WebDriver
        miDriver.get("https://twitter.com/");
        WebDriverWait asd = new WebDriverWait(miDriver, 25);
        // miDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        /*
         * try {
         * Thread.sleep(5000);
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */
        // Duration.ofSeconds(25);

        // asd.withTimeout(Duration.ofSeconds(7)).until(jorgito -> false );
        // asd.until(pepe -> false);
        // asd.notify();
        waitForSeconds(2);
        miDriver.quit();
    }

    void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void waitForGoogleSearchBar(){
        miDriver.get("https://www.google.com");
        WebDriverWait web = new WebDriverWait(miDriver, 10);
        web.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea#APjFqb")));
        miDriver.findElement(By.cssSelector("textarea#APjFqb")).sendKeys("asd");
        waitForSeconds(7);
    }


    @Test
    void waitForLoginButtonInGithub() {
        miDriver.get("https://github.com/");
        
        WebDriverWait web = new WebDriverWait(miDriver, 15);

        String xpath = "/html/body/div[1]/div[1]/header/div/div[2]/div/div/div/a";
        web.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        miDriver.findElement(By.xpath(xpath)).click();
        waitForSeconds(3);
    }


    @Test
    void waitForWikipediaCategories(){
        miDriver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");
        String searchBar = "input.cdx-text-input__input";
        WebDriverWait wiki = new WebDriverWait(miDriver, 5);
        wiki.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(searchBar))).click();
        //wiki.until(ExpectedConditions.elementToBeSelected(By.cssSelector(searchBar)));
        miDriver.findElement(By.cssSelector(searchBar)).sendKeys("derivadas parciales\n");
        String wikibody = "body.skin-vector";
        wiki.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(wikibody)));
        assertNotNull(miDriver.findElement(By.cssSelector("span#Introducción")));
        waitForSeconds(3);
    }

    @Test
    void waitForFacebookLanguageDropdown(){
        miDriver.get("https://www.facebook.com/");
        String languageList = "a._51sy[rel='dialog']";
        WebDriverWait fbList = new WebDriverWait(miDriver, 15);
        WebElement btnLangList = fbList.until(ExpectedConditions.elementToBeClickable(By.cssSelector(languageList)));
        btnLangList.click();
        fbList = new WebDriverWait(miDriver, 15);
        waitForSeconds(3);
        fbList.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div#intl-region-container")));
        
        assertNotNull(miDriver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div[2]/div/div[3]/div[2]/div[1]/table/tbody/tr/td[4]/ul/li[26]/div/a")));
    }
    


    /* Esperar a que aparezca el cuadro de búsqueda en Google

Esperar a que el botón "Login" sea clickeable en GitHub

Esperar a que se cargue la lista de categorías en Wikipedia

Esperar a que esté disponible el menú desplegable de idioma en la página de Facebook*/

    /*
     * 1. Esperar 15 segundos a que aparezca el cuadro de búsqueda en la página
     * principal de Twitter.
     * 2. Esperar un minuto a que se cargue el botón "Load more" en Medium y hacer
     * clic en él. 3. Esperar 45 segundos a que se cargue la sección de comentarios
     * de un video
     * que quieras en YouTube y contar la cantidad de comentarios. 4. Esperar a que
     * se carguen los resultados de búsqueda en Amazon y obtener el precio del
     * primer producto.
     */

    /*
     * WebDriver driver = new ChromeDriver();
     * driver.get("https://www.lapaginaquequieras.com");
     * WebDriverWait wait = new WebDriverWait(driver, 10); // Esperar hasta 10
     * segundos
     * WebElement element =
     * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unElemento"))
     * );
     */
}