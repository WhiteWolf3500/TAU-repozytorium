module.exports = {
  'Successful registration': function(browser) {

    browser
      .url('http://localhost:8080/register')
      .waitForElementVisible('body', 1000)
      .verify.title('Login')
      .setValue('input[name="username"]', "SuperCoolUsername")
      .setValue('input[name="firstname"]', "Adam")
      .setValue('input[name="lastname"]', "Jankowski")
      .click('label:nth-child(3) > .radio-inline')
      .setValue('input[name="email"]', "AdamJankowski@email.net")
      .setValue('input[name="password"]', "12345")
      .click('button')
      .assert.containsText(".alert", "Registration completed!")
      .saveScreenshot('./reports/register-success.png')
      .end();
    }
}