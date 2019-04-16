module.exports = {
  'Responsiveness test': function(browser) {

    browser
      .url('http://localhost:8080')
      .waitForElementVisible('body', 1000)
      .verify.title('Login')
      .resizeWindow('800', '600')
      .saveScreenshot('./test-reports/resolution-test-800x600.png')
      .resizeWindow('1024', '768')
      .saveScreenshot('./test-reports/resolution-test-1024x768.png')
      .resizeWindow('1280', '720')
      .saveScreenshot('./test-reports/resolution-test-1280x720.png')
      .end();
    }
}