import { BookOpen, CheckCircle, Clock, TrendingUp } from "lucide-react"

const stats = [
  {
    label: "Total Exams",
    value: "24",
    icon: BookOpen,
    color: "bg-primary/10 text-primary",
  },
  {
    label: "Completed",
    value: "18",
    icon: CheckCircle,
    color: "bg-exam-green/10 text-exam-green",
  },
  {
    label: "Avg Score",
    value: "82%",
    icon: TrendingUp,
    color: "bg-exam-orange/10 text-exam-orange",
  },
  {
    label: "Hours Spent",
    value: "36h",
    icon: Clock,
    color: "bg-exam-violet/10 text-exam-violet",
  },
]

function StatsBar() {
  return (
    <div className="stats-bar">
      {stats.map((s) => (
        <div key={s.label} className="stat-card">
          <div className={`stat-icon ${s.color}`}>
            <s.icon size={20} />
          </div>

          <div className="stat-info">
            <p className="stat-value">{s.value}</p>
            <p className="stat-label">{s.label}</p>
          </div>
        </div>
      ))}
    </div>
  )
}

export default StatsBar
