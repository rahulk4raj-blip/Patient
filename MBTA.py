class locomotive:
    def __init__(self, name, horsepower):
        self.name = name
        self.horsepower = horsepower

    def run(self)-> None:
        print(f"{self.name} is running")
        
    def getinfo(self)-> None:
        print(f"{self.name} has {self.horsepower} horsepower.")

    def __name__(self, name):
            self.name = name

    names = ["EMD F40PH", "EMD F40PH-3C", "MPI HSP46", "MPI MP36PH-3C","EMD GP40MC"]
    for name in names:
        print("Locomotive Name:")
        print(name)

    horsepower = ["3000", "3000", "4650", "3600", "3000"]
    for horsepower in horsepower:
        print("Horsepower:")
        print(horsepower)

F40PH: locomotive = locomotive("EMD F40PH", 3000)
F40PH.run()
F40PH.getinfo()

F40PH: locomotive = locomotive("EMD F40PH-3C", 3200)
F40PH.run()
F40PH.getinfo()

HSP46: locomotive = locomotive("MPI HSP46", 4650)
HSP46.run()
HSP46.getinfo()

MP36PH: locomotive = locomotive("MPI MP36PH-3C", 3600)
MP36PH.run()
MP36PH.getinfo()

GP40MC: locomotive = locomotive("EMD GP40MC", 3000)
GP40MC.run()
GP40MC.getinfo()
