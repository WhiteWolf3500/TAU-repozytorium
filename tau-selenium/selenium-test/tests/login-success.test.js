module.exports = {
  'Successful login': function(browser) {

    browser
      .url('http://localhost:8080')
      .waitForElementVisible('body', 1000)
      .verify.title('Login')
      .setValue('input[name="username"]', "test")
      .setValue('input[name="password"]', "test")
      .click('button')
      .waitForElementVisible("div > div > div > div > div > div > p:nth-child(2)", 1000)
      .assert.containsText("div > div > div > div > div > div > p", "You're logged in!")
      .saveScreenshot('./test-reports/login-success.png')
      .end();
    }
}