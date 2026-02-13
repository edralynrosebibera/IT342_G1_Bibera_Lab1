import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { GraduationCap, Search } from "lucide-react"
import ProfileMenu from "../components/ProfileMenu"
import ExamCard from "../components/ExamCard"
import StatsBar from "../components/StatsBar"
import "../assets/css/dashboard.css"

const exams = [
  { title: "Data Structures & Algorithms", subject: "Computer Science", duration: "90 min", questions: 40, participants: 1240, status: "available" },
  { title: "Linear Algebra Final", subject: "Mathematics", duration: "120 min", questions: 30, participants: 890, status: "available" },
  { title: "Modern Physics Mid-Term", subject: "Physics", duration: "60 min", questions: 25, participants: 560, status: "upcoming" },
  { title: "Database Management Systems", subject: "Computer Science", duration: "90 min", questions: 35, participants: 1100, status: "completed", score: 88 },
  { title: "Organic Chemistry Quiz", subject: "Chemistry", duration: "45 min", questions: 20, participants: 430, status: "completed", score: 72 },
  { title: "English Literature Essay", subject: "English", duration: "120 min", questions: 5, participants: 320, status: "upcoming" },
]

function Dashboard() {
  const [profile, setProfile] = useState(null)
  const [search, setSearch] = useState("")
  const navigate = useNavigate()

  useEffect(() => {
  const token = localStorage.getItem("token")

  if (!token) {
    navigate("/login")
    return
  }

  const savedUser = localStorage.getItem("user")

  if (savedUser) {
    const user = JSON.parse(savedUser)

    setProfile({
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phoneNumber: user.phoneNumber || "",
      bio: user.bio || ""
    })
  }
}, [navigate])


  const filteredExams = exams.filter(
    (exam) =>
      exam.title.toLowerCase().includes(search.toLowerCase()) ||
      exam.subject.toLowerCase().includes(search.toLowerCase())
  )

  return (
    <div className="dashboard-container">

      <header className="dashboard-header">
        <div className="header-left">
          <div className="logo-icon">
            <GraduationCap size={20} />
          </div>
          <span className="logo-text">ExamHub</span>
        </div>

        {profile && (
          <ProfileMenu profile={profile} onSave={setProfile} />
        )}
      </header>

      <main className="dashboard-main">

        <div className="welcome">
          <h1>
            Welcome back,{" "}
            <span>{profile?.firstName}</span> 👋
          </h1>
          <p>Ready for your next challenge? Pick an exam below.</p>
        </div>

        <StatsBar />

        <div className="search-section">
          <div className="search-box">
            <Search size={16} />
            <input
              type="text"
              placeholder="Search exams..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>
        </div>

        <div className="exam-grid">
          {filteredExams.map((exam) => (
            <ExamCard key={exam.title} {...exam} />
          ))}
        </div>

        {filteredExams.length === 0 && (
          <div className="empty-state">
            <p className="title">No exams found</p>
            <p className="subtitle">Try a different search term</p>
          </div>
        )}
      </main>
    </div>
  )
}

export default Dashboard
