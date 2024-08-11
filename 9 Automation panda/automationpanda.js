// cypress/fixtures/pages.json
{
    "homepageTitle": "Want to Practice Test Automation? Try These Demo Sites! – Automation Panda",
    "speakingPageTitle": "Speaking – Automation Panda",
    "keynoteText": "Keynote Addresses"
  }
  
  // cypress/integration/automationpanda.spec.js
  describe('Automation Panda Site Tests', () => {
    before(() => {
      // Load fixture data
      cy.fixture('pages').as('pagesData');
    });
  
    beforeEach(() => {
      // Any setup steps can be placed here
    });
  
    it('Launch the URL and verify the title', function () {
      cy.visit('https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites/');
      
      // Verify the title of the homepage
      cy.title().should('eq', this.pagesData.homepageTitle);
    });
  
    it('Click on Speaking and verify the title of the page', function () {
      // Click on the "Speaking" link
      cy.get('a[href="https://automationpanda.com/speaking/"]').click();
      
      // Verify the title of the Speaking page
      cy.title().should('eq', this.pagesData.speakingPageTitle);
    });
  
    it('Verify Keynote Addresses is present and verify the text', function () {
      // Check if "Keynote Addresses" is present
      cy.contains(this.pagesData.keynoteText).should('be.visible');
    });
  
    afterEach(() => {
      // Any cleanup steps can be placed here
    });
    
    after(() => {
      // Final cleanup, if needed
    });
  });
  