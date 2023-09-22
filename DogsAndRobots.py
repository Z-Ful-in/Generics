class Dogs:
    def speak(self):
        print("Woof!")
    def sit(self):
        print("Sitting")
    def reproduce(self):
        pass
class Robot:
    def speak(self):
        print("Click!")
    def sit(self):
        print("Clank!")
    def oilChange(self):
        pass
def perform(anything):
    anything.speak()
    anything.sit()
    anything.reproduce()
    anything.oilChange()