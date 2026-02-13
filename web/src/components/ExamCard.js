import { Clock, FileText, Trophy, Users, ArrowRight } from "lucide-react"

const statusStyles = {
  available: "bg-primary/10 text-primary border-primary/20",
  upcoming: "bg-exam-orange/10 text-exam-orange border-exam-orange/20",
  completed: "bg-exam-green/10 text-exam-green border-exam-green/20",
}

const statusLabels = {
  available: "Start Now",
  upcoming: "Upcoming",
  completed: "Completed",
}

function ExamCard({
  title,
  subject,
  duration,
  questions,
  participants,
  status,
  score,
}) {
  return (
    <div className="group relative bg-card rounded-xl border border-border p-5 hover:shadow-lg hover:border-primary/30 transition-all duration-300 cursor-pointer">
      <div className="flex items-start justify-between mb-3">
        <span className="text-xs font-semibold uppercase tracking-wider text-muted-foreground">
          {subject}
        </span>

        <span
          className={`text-xs font-semibold px-2.5 py-1 rounded-full border ${statusStyles[status]}`}
        >
          {statusLabels[status]}
        </span>
      </div>

      <h3 className="text-lg font-bold text-foreground mb-3 group-hover:text-primary transition-colors">
        {title}
      </h3>

      <div className="flex flex-wrap gap-4 text-sm text-muted-foreground mb-4">
        <span className="flex items-center gap-1.5">
          <Clock size={16} /> {duration}
        </span>

        <span className="flex items-center gap-1.5">
          <FileText size={16} /> {questions} Qs
        </span>

        <span className="flex items-center gap-1.5">
          <Users size={16} /> {participants}
        </span>
      </div>

      {status === "completed" && score !== undefined && (
        <div className="flex items-center gap-2 mb-3">
          <Trophy className="h-4 w-4 text-exam-orange" />
          <span className="text-sm font-semibold text-foreground">
            Score: {score}%
          </span>

          <div className="flex-1 h-2 bg-muted rounded-full overflow-hidden">
            <div
              className="h-full bg-exam-green rounded-full transition-all"
              style={{ width: `${score}%` }}
            />
          </div>
        </div>
      )}

      {status === "available" && (
        <button className="w-full mt-1 flex items-center justify-center gap-2 py-2.5 rounded-lg bg-primary text-primary-foreground font-semibold text-sm hover:opacity-90 transition-opacity">
          Start Exam <ArrowRight size={16} />
        </button>
      )}
    </div>
  )
}

export default ExamCard
