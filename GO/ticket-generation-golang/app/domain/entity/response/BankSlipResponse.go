package response

type BankSlipResponse struct {
	Id           string
	DueData      string
	TotalInCents string
	Customer     string
	Status       string
}
